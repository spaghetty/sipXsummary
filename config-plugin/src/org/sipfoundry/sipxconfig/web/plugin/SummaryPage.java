package org.sipfoundry.sipxconfig.web.plugin;


import java.util.Set;
import java.util.List;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.annotations.InjectObject;
import org.sipfoundry.sipxconfig.components.SipxBasePage;
import org.sipfoundry.sipxconfig.summary.Summary;
import org.sipfoundry.sipxconfig.address.Address;
import org.sipfoundry.sipxconfig.common.CoreContext;
import org.sipfoundry.sipxconfig.domain.DomainManager;
import org.sipfoundry.sipxconfig.phone.PhoneContext;
import org.sipfoundry.sipxconfig.acccode.AuthCodeManager;
import org.sipfoundry.sipxconfig.dialplan.DialingRule;
import org.sipfoundry.sipxconfig.dialplan.DialingRuleType;
import org.sipfoundry.sipxconfig.dialplan.InternalRule;
import org.sipfoundry.sipxconfig.intercom.IntercomManager;
import org.sipfoundry.sipxconfig.parkorbit.ParkOrbit;
import org.sipfoundry.sipxconfig.gateway.GatewayContext;
import org.sipfoundry.sipxconfig.gateway.Gateway;
import org.sipfoundry.sipxconfig.commserver.Location;
import org.sipfoundry.sipxconfig.snmp.ProcessDefinition;
import org.sipfoundry.sipxconfig.commserver.LocationsManager;
import org.sipfoundry.sipxconfig.upload.UploadManager;
import org.sipfoundry.sipxconfig.upload.Upload;
import org.sipfoundry.sipxconfig.paging.PagingContext;
import org.sipfoundry.sipxconfig.acccode.AuthCodes;

public abstract class SummaryPage extends SipxBasePage {
    public static final String PAGE = "plugin/SummaryPage";
    /**
     * inject your service into the page to perform operations, read/write data, etc. Tapestry integration
     * performs the magic of setting the instance on this page, you just have to get the bean name correct.
     */
    
    @InjectObject("spring:sipxsummary")
    public abstract Summary getSummary();
    

    public boolean isPagingEnabled() {
	Summary e = getSummary();
	return (e.getFeatureManager().isFeatureEnabled(PagingContext.FEATURE));
    }

    public String getDnsAddress() {
	Summary e = getSummary();
	if(e.getDnsManager().getSettings().getDnsForwarders().size() == 0)
            return null;
        else
            return ((Address)e.getDnsManager().getSettings().getDnsForwarders().get(0)).getAddress();
    }

    public String getNtpAddres() {
	Summary e = getSummary();
	if(e.getNtpManager().getSettings().getNtpServers().size() == 0)
            return null;
        else
            return (String)e.getNtpManager().getSettings().getNtpServers().get(0);
    }

    public UploadManager getUploadManager() {
	Summary e = getSummary();
	return e.getUploadManager();
    }

    public LocationsManager getLocationsManager() {
	Summary e = getSummary();
	return e.getLocationsManager();
    }

    public List<ProcessDefinition> getInstalledServices(Location location)
    {
	Summary e = getSummary();
        return e.getSnmpManager().getProcessDefinitions(location);
    }

    public GatewayContext getGatewayContext() {
	Summary e = getSummary();
	return e.getGatewayContext();
    }

    public IntercomManager getIntercomManager() {
	Summary e = getSummary();
	if (e.getFeatureManager().isFeatureEnabled(e.getIntercomManager().FEATURE)) {
	    return e.getIntercomManager();
	} else {
	    return null;
	}
    }

    public String getParkExtension()
    {
	Summary e = getSummary();
	String result="";
        if(!e.getParkOrbitContext().getParkOrbits().isEmpty() &&
	     e.getFeatureManager().isFeatureEnabled(e.getParkOrbitContext().FEATURE)) {
            for ( ParkOrbit p: e.getParkOrbitContext().getParkOrbits()) {
		result = (result.equals("")) ? p.getExtension() : result + ", " + p.getExtension();
	    }
	    return result;
	}
        else
            return "NaN";
    }

    public String getVMRoute() {
	Summary e = getSummary();
	List<DialingRule> list = e.getDialPlanContext().getRules();
	for ( DialingRule dialingRule: list ) {
	    if (dialingRule.getType() == DialingRuleType.INTERNAL)
		return ((InternalRule)dialingRule).getVoiceMailPrefix();
	}
	return null;
    }

    public CoreContext getCoreContext() {
        Summary e = getSummary();
        return e.getCoreContext();
    }

    public AuthCodeManager getAuthCodeManager() {
	Summary e = getSummary();
	if ( e.getFeatureManager().isFeatureEnabled(AuthCodes.FEATURE )) { 
	    return e.getAuthCodeManager();
	} else {
	    return null;
	}
    }

    public DomainManager getDomainManager() {
	Summary e = getSummary();
	return e.getDomainManager();
    }

    public PhoneContext getPhoneContext() {
	Summary e = getSummary();
	return e.getPhoneContext();
    }

    public abstract String getAlias();
    public abstract void setAlias(String s);

    public abstract Gateway getGateway();
    public abstract void setGateway(Gateway g);
    
    public abstract Location getCurLocation();
    public abstract void setCurLocation(Location l);
    
    public abstract ProcessDefinition getCurService();
    public abstract void setCurService(ProcessDefinition p);

    public abstract Upload getUpload();
    public abstract void setUpload(Upload u);


    public String getSubnetMask() {
	Summary e = getSummary();
	return e.getSubnetMask();
    }

    public String getDefaultGw() {
	Summary e = getSummary();
	return e.getDefaultGw();
    }

    public String getLocalIp() {
	Summary e = getSummary();
	return e.getLocationsManager().getPrimaryLocation().getAddress();
    }

    public String getFqdn() {
	Summary e = getSummary();
	return e.getLocationsManager().getPrimaryLocation().getFqdn();
    }
    
    public void setSystemDescription(String d) {
	Summary e = getSummary();
	e.setSystemDescription(d);
    }
    
    public String getSystemDescription() {
	Summary e = getSummary();
	return e.getSystemDescription();
    }

    public void setErrorText(String err) {
	Summary e = getSummary();
	e.setErrorText(err);
    }
    
    public String getErrorText() {
	Summary e = getSummary();
	return e.getErrorText();
    }

    public void doSubmit(IRequestCycle irequestcycle)
    {
	Summary e = getSummary();
	e.doWrite();
    }
    
}