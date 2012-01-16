<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<h:form>
	<h:commandButton action="#{messages.send}" value="#{msg['action.send']}"/>
	<rich:datascroller id="messagesListScroller" align="left"
		for="messagesList" renderIfSinglePage="false"/>
	<rich:dataTable width="100%" id="messagesList" rows="20"
		value="#{messages.all}" var="message">
		<f:facet name="header">
			<h:outputText value="#{msg['tab.messages']}"/>
		</f:facet>
		<rich:column>
			<f:facet name="header">
				<h:outputText value="#{msg['person.phone']}"/>
			</f:facet>
			<h:outputText id="phone" value="#{message.person.phone}"/>
		</rich:column>
		<rich:column>
			<f:facet name="header">
				<h:outputText value="#{msg['sms.message']}"/>
			</f:facet>
			<h:outputText id="text" value="#{message.template.text}"/>
		</rich:column>
		<rich:column>
			<f:facet name="header">
				<h:outputText value="#{msg['sms.status']}"/>
			</f:facet>
			<h:outputText id="status" value="#{message.status}"/>
		</rich:column>
	</rich:dataTable>
</h:form>
