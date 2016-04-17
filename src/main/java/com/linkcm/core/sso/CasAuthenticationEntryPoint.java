package com.linkcm.core.sso;

/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
*
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasig.cas.client.util.CommonUtils;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CasAuthenticationEntryPoint implements AuthenticationEntryPoint {
   //~ Instance fields ================================================================================================
   private ServiceProperties serviceProperties;

   private String loginUrl;
   
   private String serverPort;
   
   private String clientPort;

   /**
    * Determines whether the Service URL should include the session id for the specific user.  As of CAS 3.0.5, the
    * session id will automatically be stripped.  However, older versions of CAS (i.e. CAS 2), do not automatically
    * strip the session identifier (this is a bug on the part of the older server implementations), so an option to
    * disable the session encoding is provided for backwards compatibility.
    *
    * By default, encoding is enabled.
    * @deprecated since 3.0.0 because CAS is currently on 3.3.5.
    */
   @Deprecated
   private boolean encodeServiceUrlWithSessionId = true;

   //~ Methods ========================================================================================================

   public final void commence(final HttpServletRequest servletRequest, final HttpServletResponse response,
           final AuthenticationException authenticationException) throws IOException, ServletException {
       StringBuilder tempLoginUrl=new StringBuilder();
       StringBuilder serverUrl=new StringBuilder();
       StringBuilder clientUrl=new StringBuilder();
       
       tempLoginUrl.append(servletRequest.getScheme()).append("://");
       tempLoginUrl.append(servletRequest.getServerName());
       
       if(serverPort!=null && !"".equals(serverPort))
       {
    	   serverUrl.append(tempLoginUrl).append(":").append(serverPort);
       }
       serverUrl.append("/cas/login");
       loginUrl=serverUrl.toString();
      
       if(clientPort!=null && !"".equals(clientPort))
       {
    	   clientUrl.append(tempLoginUrl).append(":").append(servletRequest.getServerPort());
    	   clientUrl.append(servletRequest.getContextPath());
           clientUrl.append("/j_spring_cas_security_check");
           serviceProperties.setService(clientUrl.toString());
       }
       
       final String urlEncodedService = createServiceUrl(servletRequest, response);
       final String redirectUrl = createRedirectUrl(urlEncodedService);

       preCommence(servletRequest, response);
       response.sendRedirect(redirectUrl);
   }

   /**
    * Constructs a new Service Url.  The default implementation relies on the CAS client to do the bulk of the work.
    * @param request the HttpServletRequest
    * @param response the HttpServlet Response
    * @return the constructed service url.  CANNOT be NULL.
    */
   protected String createServiceUrl(final HttpServletRequest request, final HttpServletResponse response) {
       return CommonUtils.constructServiceUrl(null, response, this.serviceProperties.getService(), null, this.serviceProperties.getArtifactParameter(), this.encodeServiceUrlWithSessionId);
   }

   /**
    * Constructs the Url for Redirection to the CAS server.  Default implementation relies on the CAS client to do the bulk of the work.
    *
    * @param serviceUrl the service url that should be included.
    * @return the redirect url.  CANNOT be NULL.
    */
   protected String createRedirectUrl(final String serviceUrl) {
       return CommonUtils.constructRedirectUrl(this.loginUrl, this.serviceProperties.getServiceParameter(), serviceUrl, this.serviceProperties.isSendRenew(), false);
   }

   /**
    * Template method for you to do your own pre-processing before the redirect occurs.
    *
    * @param request the HttpServletRequest
    * @param response the HttpServletResponse
    */
   protected void preCommence(final HttpServletRequest request, final HttpServletResponse response) {

   }

   /**
    * The enterprise-wide CAS login URL. Usually something like
    * <code>https://www.mycompany.com/cas/login</code>.
    *
    * @return the enterprise-wide CAS login URL
    */
   public final String getLoginUrl() {
       return this.loginUrl;
   }

   public final ServiceProperties getServiceProperties() {
       return this.serviceProperties;
   }

   public final void setLoginUrl(final String loginUrl) {
       this.loginUrl = loginUrl;
   }

   public final void setServiceProperties(final ServiceProperties serviceProperties) {
       this.serviceProperties = serviceProperties;
   }

   /**
    * Sets whether to encode the service url with the session id or not.
    *
    * @param encodeServiceUrlWithSessionId whether to encode the service url with the session id or not.
    * @deprecated since 3.0.0 because CAS is currently on 3.3.5.
    */
   @Deprecated
   public final void setEncodeServiceUrlWithSessionId(final boolean encodeServiceUrlWithSessionId) {
       this.encodeServiceUrlWithSessionId = encodeServiceUrlWithSessionId;
   }

   /**
    * Sets whether to encode the service url with the session id or not.
    * @return whether to encode the service url with the session id or not.
    *
    * @deprecated since 3.0.0 because CAS is currently on 3.3.5.
    */
   @Deprecated
   protected boolean getEncodeServiceUrlWithSessionId() {
       return this.encodeServiceUrlWithSessionId;
   }

	public String getServerPort()
	{
		return serverPort;
	}
	
	public void setServerPort(String serverPort)
	{
		this.serverPort = serverPort;
	}
	
	public String getClientPort()
	{
		return clientPort;
	}
	
	public void setClientPort(String clientPort)
	{
		this.clientPort = clientPort;
	}  
}

