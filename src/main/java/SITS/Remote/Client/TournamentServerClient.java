package SITS.Remote.Client;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import SITS.Remote.Network.dto.RegistrationRequest;
import SITS.Remote.Server.NetworkedTournament;

public class TournamentServerClient {
	
	private String server_url;
	private RestTemplate restTemplate;
	
	public TournamentServerClient(String server_url) 
	{
		this.server_url = server_url;
		this.restTemplate = new RestTemplate();
	}
	
	
	
	//methods for this. 
	//why are we using rest template and not rest client again? 
	//anyways I digress. 
	/*diego designed this whole thing on a Saturday alone without us and didn't tell us until
	 * after. then proceeded to ghost us until after the presentation of the design which was like 
	 * 5 days.*/
	
	//and since the Remote PArticipant is using Remote Template i guess I'll keep the same style

	
	/*I feel like this should be more like : 

	public TournamentServerClient(String server_url) 
	{
    	this.server_url = server_url;
    	this.client = RestClient.create(); 

    }
    or even the whole thing in Main() like how the restPassowrdServer 
    Example looks like.
    
    if im keeping with the design document then the list tounaments would look closer to this
    
    public List<?> listTournaments() {
         return client.get()
				.uri(server_url + "/tournaments")
                .retrieve()
                .body(List.class);
	} 
	like why am I doing this stupid rest template stuff. anyways. 
	in the pursuit of knowledge i guess. 
	
	i did not digress in my earlier comment. 
	
	 * */
	
	/**
	 * @return the server_url for testing
	 */
	public String getServer_url() {
		return server_url;
	}

	/**
	 * @return the restTemplate for testing
	 */
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}


	//should only be read only here so I guess this <?> is fine 
	public List<?> listTournaments()
	{
		return restTemplate.getForObject(server_url+ "/tournaments",List.class); 
	} 
	
	public void register(String tournamentId, String name, String ip, int port) 
	{
		RegistrationRequest request = new RegistrationRequest(name,ip,port); 
		restTemplate.postForObject(
				server_url +"/register/"+ tournamentId,request, String.class); 
	
	}

}
