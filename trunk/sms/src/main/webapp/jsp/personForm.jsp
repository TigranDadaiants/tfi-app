<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<rich:modalPanel id="personEditor" width="350" height="220"
	domElementAttachment="parent">
	<f:facet name="header">
		<h:outputText value="#{msg['person.edit']}"/>
	</f:facet>
	<a4j:form>
		<h:panelGrid columns="3">
			<h:outputLabel for="editLastName">
				<h:outputText value="#{msg['person.edit.lastName']}"/>
			</h:outputLabel>
			<h:inputText id="editLastName" required="true"
				value="#{persons.person.lastName}"
				requiredMessage="#{msg['errors.lastName.required']}"/>
			<h:message for="editLastName"/>
			<h:outputLabel for="editFirstName">
				<h:outputText value="#{msg['person.edit.firstName']}"/>
			</h:outputLabel>
			<h:inputText id="editFirstName" required="true"
				value="#{persons.person.firstName}"
				requiredMessage="#{msg['errors.firstName.required']}"/>
			<h:message for="editFirstName"/>
			<h:outputLabel for="editMiddleName">
				<h:outputText value="#{msg['person.edit.middleName']}"/>
			</h:outputLabel>
			<h:inputText id="editMiddleName"
				value="#{persons.person.middleName}"/>
			<h:message for="editMiddleName"/>
			<h:outputLabel for="editPhone">
				<h:outputText value="#{msg['person.edit.phone']}"/>
			</h:outputLabel>
			<h:inputText id="editPhone" required="true"
				value="#{persons.person.phone}"
				requiredMessage="#{msg['errors.phone.required']}"/>
			<h:message for="editPhone"/>
		</h:panelGrid>
		<h:panelGrid columns="2">
			<a4j:commandButton action="#{persons.save}"
				reRender="mainPanel" value="#{msg['action.save']}"
				oncomplete="Richfaces.hideModalPanel('personEditor');"/>
			<a4j:region>
				<a4j:commandButton reRender="mainPanel"
					value="#{msg['action.cancel']}"
					oncomplete="Richfaces.hideModalPanel('personEditor');"/>
			</a4j:region>
		</h:panelGrid>
	</a4j:form>
</rich:modalPanel>
