
public class Screen {
	
	// properties
	private String head ="" ;
	private String body ="" ;
	private String footer ="" ;
	
	// default constructor
	public Screen() {}

	// overloaded constructor
	public Screen(String head, String body, String footer) {
		this.head = head;
		this.body = body;
		this.footer = footer;
	}

	// get header
	public String getHead() {
		return head;
	}

	// set header
	public void setHead(String head) {
		this.head = head;	 
	}

	// get body
	public String getBody() {
		return body;
	}

	// set body
	public void setBody(String body) {
		this.body = body;
	}

	// get footer
	public String getFooter() {
		return footer;
	}

	// set footer
	public void setFooter(String footer) {
		this.footer = footer;		 
	}
	
	// print head, body and footer
	public void printScreen ( ) {
		System.out.print(toString());		
	}

	// concat head, body and footer
	@Override
	public String toString() {
		return getHead() + getBody() + getFooter();
	}

	
}
