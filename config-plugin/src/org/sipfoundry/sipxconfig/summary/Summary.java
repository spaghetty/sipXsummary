package org.sipfoundry.sipxconfig.summary;

import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;

import org.sipfoundry.sipxconfig.setting.BeanWithSettingsDao;
import org.sipfoundry.sipxconfig.common.CoreContext;
import org.sipfoundry.sipxconfig.commserver.LocationsManager;
import org.sipfoundry.sipxconfig.domain.DomainManager;
import org.sipfoundry.sipxconfig.phone.PhoneContext;
import org.sipfoundry.sipxconfig.acccode.AuthCodeManager;
import org.sipfoundry.sipxconfig.dialplan.DialPlanContext;
import org.sipfoundry.sipxconfig.intercom.IntercomManager;
import org.sipfoundry.sipxconfig.parkorbit.ParkOrbitContext;
import org.sipfoundry.sipxconfig.gateway.GatewayContext;
import org.sipfoundry.sipxconfig.snmp.SnmpManager;
import org.sipfoundry.sipxconfig.upload.UploadManager;
import org.sipfoundry.sipxconfig.dns.DnsManager;
import org.sipfoundry.sipxconfig.time.NtpManager;
import org.sipfoundry.sipxconfig.cfgmgt.ConfigManager;


public class Summary {
    private CoreContext m_coreContext;
    private LocationsManager m_locationsManager;
    private DomainManager m_domainManager;
    private PhoneContext m_phoneContext;
    private AuthCodeManager m_authCodeManager;
    private DialPlanContext m_dialPlanContext;
    private IntercomManager m_intercomManager;
    private ParkOrbitContext m_parkOrbitContext;
    private GatewayContext m_gatewayContext;
    private SnmpManager m_snmpManager;
    private UploadManager m_uploadManager;
    private DnsManager m_dnsManager;
    private NtpManager m_ntpManager;
    private ConfigManager m_configManager;

    private String m_systemDescription;
    private String m_errorText;

    public int getNumberOfUsers() {
        return m_coreContext.getAllUsersCount();
    }


    public ConfigManager getConfigManager() {
    	return m_configManager;
    }

    public void setConfigManager(ConfigManager d) {
	m_configManager = d;
    }


    public DnsManager getDnsManager() {
	return m_dnsManager;
    }

    public void setDnsManager(DnsManager d) {
	m_dnsManager = d;
    }

    public NtpManager getNtpManager() {
	return m_ntpManager;
    }

    public void setNtpManager(NtpManager n) {
	m_ntpManager = n;
    }

    public UploadManager getUploadManager() {
	return m_uploadManager;
    }

    public void setUploadManager(UploadManager u) {
	m_uploadManager = u;
    }
        
    public SnmpManager getSnmpManager() {
	return m_snmpManager;
    }

    public void setSnmpManager(SnmpManager s) {
	m_snmpManager = s;
    }

    public GatewayContext getGatewayContext() {
	return m_gatewayContext;
    }

    public void setGatewayContext(GatewayContext gatewayContext) {
	m_gatewayContext = gatewayContext;
    }

    public ParkOrbitContext getParkOrbitContext() {
	return m_parkOrbitContext;
    }

    public void setParkOrbitContext(ParkOrbitContext parkOrbitContext) {
	m_parkOrbitContext = parkOrbitContext;
    }

    public IntercomManager getIntercomManager() {
	return m_intercomManager;
    }

    public void setIntercomManager(IntercomManager intercomManager) {
	m_intercomManager = intercomManager;
    }

    public DialPlanContext getDialPlanContext(){
	return m_dialPlanContext;
    }

    public void setDialPlanContext(DialPlanContext dialPlanContext) {
	m_dialPlanContext = dialPlanContext;
    }

    public AuthCodeManager getAuthCodeManager() {
	return m_authCodeManager;
    }

    public void setAuthCodeManager(AuthCodeManager authCodeManager) {
	m_authCodeManager = authCodeManager;
    }

    public PhoneContext getPhoneContext() {
	return m_phoneContext;
    }

    public void setPhoneContext(PhoneContext phoneContext) {
	m_phoneContext = phoneContext;
    }

    public DomainManager getDomainManager() {
	return m_domainManager;
    }

    public void setDomainManager(DomainManager domainManager) {
	m_domainManager = domainManager;
    }

    public CoreContext getCoreContext() {
        return m_coreContext;
    }

    public void setCoreContext(CoreContext coreContext) {
        m_coreContext = coreContext;
    }

    public LocationsManager getLocationsManager() {
	return m_locationsManager;
    }

    public void setLocationsManager(LocationsManager locationsManager) {
	m_locationsManager = locationsManager;
    }

    public void setSystemDescription(String s)
    {
        m_systemDescription = s;
    }


    public String getSystemDescription()
    {
	if(m_systemDescription==null) {
	    try {
		File dir = m_configManager.getGlobalDataDirectory();
		m_systemDescription = IOUtils.toString(new FileInputStream(new File(dir, "sipxsummary.txt")));
	    } catch(Exception exception) {
		m_systemDescription="";
	    }
	}

	return m_systemDescription;
    }

    public String getErrorText()
    {
        return m_errorText;
    }
    
    public void setErrorText(String e)
    {
	m_errorText = e;
    }
    

    public void doWrite() {
	m_errorText = null;
	File dir = m_configManager.getGlobalDataDirectory();
        try
	    {
		FileWriter filewriter = new FileWriter(new File(dir, "sipxsummary.txt"), false);
		filewriter.write(m_systemDescription);
		filewriter.close();
	    }
        catch(Exception exception)
	    {
		m_errorText = (new StringBuilder()).append("Error saving description - ").append(exception.getMessage()).append(": ").append(exception.toString()).toString();
		exception.printStackTrace();
	    }
    }

}