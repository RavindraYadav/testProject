package utils;

public class Constant {
	//Reseller work flow
	
	//Web client login information
	public final static  String emailID="adobenet\\virsharm";
	public final static  String password="Adobe$01";
	public final static String Dom_click_logon_xpath=".//*[@id='tblMid']/tbody/tr[5]/td/table/tbody/tr[2]/td[3]/input[1]";
	
	public final static String Dom_click_Search_button_xpath=".//*[@id='schBtn']/img";
	public final static String Dom_click_Searched_email_xpath=".//*[@id='frm']/table/tbody/tr[2]/td[3]/table/tbody/tr[3]/td/div/table/tbody/tr[3]/td[6]/h1/a";
	public final static String Dom_logout_from_mail="lo";
	
	
	//Reseller and add customer variables
	public final static String resellerusername="virsharm+stgreseller@adobetest.com"; //change reseller name from stage to dev or vice versa
	public final static String resellerloginpass="tester";
	public final static String customerdashboard_btn=".//*[@id='ccm_reseller_home_div_vendorName']/span[2]/a";
	public final static String Add_cus_emailid="virsharm+cusjan30_010@adobetest.com";// Before run alway's change Add customer email ID
	
	//Add Customer dialog
	public final static String addcustomer_btn="ccm_reseller_custDash_a_AddCust";
	public final static String org_name_text_box="input_orgname";
	
	public final static String orgname="Automation Testing jan24_03"; //Change organisation Name
	
	public final static String sel_mgket_seg_dropdown="//*[@id='input_marketseg_chzn']/a";
	public final static String sel_mgket_seg_type="input_marketseg_chzn_o_1";
	public final static String Sel_country_dropdown=".//*[@id='input_country_chzn']/a";
	public final static String Sel_country__from_dropdown="//*[@id='input_country_chzn_o_214']";
	public final static String sel_state_dropdown="//*[@id='input_states_chzn']/a";
	public final static String sel_state_from_dropdown="//*[@id='input_states_chzn_o_9']";
	
	//Sign out variables
	public final static String Dom_sign_out_dropdown="ccm_reseller_home_img_drpdwnArrow";
	public final static String Dom_sign_out_btn_xpath=".//*[@id='shSignOut']/a";
	
	public final static String Dom_admin_console_btn=".//*[@id='content']/div/div/a";
	
	
	
	//Add more seats
	public final static String vipno="0AE5F6DAED4758CD145B"; 
	public final static String Dom_add_more_seat_add_product=".//*[@id='content']/div[2]/div[3]/div/span";
	
	
	
	//Add sales rep variables
	public final static String salesrepemailID="virsharm+salesrepjan24_03@adobetest.com"; //Change sales rep email ID
	public final static String salesrep_first_name="1234aaa";
	public final static String salesrep_last_name="adobe";

	
	
	
}
