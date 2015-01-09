package com.geo.gwt.client;

import com.geo.gwt.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Label lbl1 = new Label("Grade 3 Exam Results");
		lbl1.setTitle("NPS School Website");
		lbl1.setSize("250px", "30px");
	
		//lbl1.setText("abcdefg", HasDirection.Direction.RTL);
			lbl1.addStyleName("gwt-Red-Border");
			lbl1.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		
		
		Image img = new Image();
		img.setUrl("images/school.jpg");
		img.setSize("200px", "200px");
		img.addStyleName("gwt-Red-Border");
		
		Anchor anchor = new Anchor("Google",false,"http://www.google.com","_blank");
		
		VerticalPanel lvpanel = new VerticalPanel();
		VerticalPanel rvpanel = new VerticalPanel();
		HorizontalPanel hpanel = new HorizontalPanel();
		
		HTML html = new HTML("<b>Enter Student Name</b>");
		
		
		final TextBox tb = new TextBox();
		tb.setWidth("250px");
		tb.setHeight("20px");
        tb.setTitle("Enter Name");
        //tb.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
      /*  tb.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				// TODO Auto-generated method stub
				Window.alert(""+event.getNativeKeyCode() );
			}
		});*/
		
        final CheckBox cb = new CheckBox();
        cb.setChecked(false);
        cb.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				tb.setValue(""+cb.getValue());
			}
		});
        

		final RichTextArea r = new RichTextArea();
		r.setSize("250px", "150px");
		r.setHTML("<b><font color=black><ul>Results</ul></font></b>");
		r.setStyleName("back");
		
		
		Button button = new Button("Get Results");
		button.setWidth("250px");
		button.setHeight("30px");
		button.addStyleName("gwt-button");
	
		button.addClickHandler(new ClickHandler(){
						
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
				
				
				greetingService.getMarks(tb.getText(),
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								r.setHTML("<font color=red><b>ERROR: Invalid student !</b></font>");
							}

							public void onSuccess(String result) {
								r.setHTML(result);
							}
						});
				
				
				
			}
		});
		
		ListBox lb = new ListBox();
		lb.addItem("Jan");
		lb.addItem("Feb");
		lb.addItem("Mar");
		lb.addItem("Apr");
		lb.addItem("May");
		lb.setHeight("1");
		
		MultiWordSuggestOracle o = new MultiWordSuggestOracle();
		o.add("Rajesh");
		o.add("Rajeev");
		o.add("Sita");
		o.add("Anish");
		o.add("Anil");
		o.add("Bejoy");
		o.add("Bibin");
		o.add("Bini");
		
		SuggestBox sb = new SuggestBox(o);
		
		
		HTML navHtml = new HTML("<aside><nav><a href='./'><b>Home</b></a> <br>	<a href='/video.html'><b>Videos</b></a><br>	<a href='/link.html'><b>Link</b></a><BR><BR> <video width='200' height='120' controls><source src='images/small.mp4' type='video/mp4'>Your browser does not support the video tag.</video>	</nav></aside>");

		lvpanel.setStyleName("lpanel");
		lvpanel.setSize("200px", "400px");
		lvpanel.add(img);
		
		//panel.add(lb);
		lvpanel.add(navHtml);
		lvpanel.add(anchor);
		
		
		rvpanel.add(lbl1);
		rvpanel.setSize("350px", "400px");
		rvpanel.setStyleName("rpanel");
		rvpanel.add(html);
		rvpanel.add(tb);
		rvpanel.add(r);
		//rvpanel.add(cb);
		rvpanel.add(button);
		rvpanel.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
	hpanel.add(lvpanel);
	hpanel.add(rvpanel);
	hpanel.setStyleName("style");
		
		RootPanel.get("gwtContainer").add(hpanel);

	}
}
