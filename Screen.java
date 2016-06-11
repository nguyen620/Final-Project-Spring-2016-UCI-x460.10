
public class Screen {
	
	

	/*
	 * Properties
	 */
	private String head ="" ;
	private String body ="" ;
	private String footer ="" ;
	
	
	
	public String getHead() {
		return head;
	}



	public void setHead(String head) {
		this.head = head;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	public String getFooter() {
		return footer;
	}



	public void setFooter(String footer) {
		this.footer = footer;
	}


	/*
	* Needs to return concat of header, body , footer
	*/

	@Override
	public String toString() {
		return getHead()  +  getBody() + getFooter();
	}

	
}
