<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich" %>
<rich:panel header="#{msg['tab.sms']}">
	<a4j:form id="sendSmsForm">
		<h:panelGrid columns="1">
			<h:outputLabel for="message" value="#{msg['sms.message']}"/>
			<h:inputTextarea id="message" cols="50" rows="10"
				value="#{smsSender.message.text}" required="true"/>
			<h:message for="message"/>
			<h:commandButton action="#{smsSender.send}"
				value="#{msg['action.send']}"/>
		</h:panelGrid>
		<rich:datascroller id="personsScroller" align="left"
			for="persons" renderIfSinglePage="false"/>
		<rich:dataTable id="persons" value="#{smsSender.persons}"
			var="person" rows="20">
			<rich:column>
				<h:selectBooleanCheckbox immediate="true"
					value="#{person.checked}"/>
			</rich:column>
			<rich:column>
				<h:outputText value="#{person.object.phone}"/>
			</rich:column>
			<rich:column>
				<h:outputText
					value="#{person.object.lastName} #{person.object.firstName}"/>
			</rich:column>
		</rich:dataTable>
	</a4j:form>
</rich:panel>
