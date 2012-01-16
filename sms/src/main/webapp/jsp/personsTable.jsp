<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j"%>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich"%>
<a4j:form>
	<rich:datascroller id="personsListScroller" align="left"
		for="personsList" renderIfSinglePage="false"/>
	<rich:dataTable width="100%" id="personsList" rows="20"
		value="#{persons.all}" var="person" columnsWidth="25,25">
		<f:facet name="header">
			<h:outputText value="#{msg['tab.persons']}"/>
		</f:facet>
		<rich:column>
			<f:facet name="header">
				<a4j:commandLink action="#{persons.add}"
					reRender="personEditor"
					oncomplete="Richfaces.showModalPanel('personEditor', {top:100});">
					<h:graphicImage value="../images/add.png"/>
					<rich:toolTip value="#{msg['action.add']}"/>
				</a4j:commandLink>
			</f:facet>
			<a4j:commandLink action="#{persons.edit}" reRender="personEditor"
				oncomplete="Richfaces.showModalPanel('personEditor', {top:100});">
				<f:setPropertyActionListener target="#{persons.person}"
					value="#{person}"/>
				<h:graphicImage value="../images/edit.png"/>
					<rich:toolTip value="#{msg['action.edit']}#{person.lastName}"/>
			</a4j:commandLink>
		</rich:column>
		<rich:column>
			<f:facet name="header">
				<a4j:commandLink reRender="importLab"
					oncomplete="Richfaces.showModalPanel('importPersons',{top:200});">
					<h:graphicImage value="../images/import.jpg"/>
					<rich:toolTip value="#{msg['action.import']}"/>
				</a4j:commandLink>
			</f:facet>
			<a4j:commandLink action="#{persons.delete}" reRender="mainPanel">
				<f:setPropertyActionListener target="#{persons.person}"
					value="#{person}"/>
				<h:graphicImage value="../images/delete.png"/>
				<rich:toolTip value="#{msg['action.delete']}#{person.lastName}"/>
			</a4j:commandLink>
		</rich:column>
		<rich:column sortBy="#{person.lastName}">
			<f:facet name="header">
				<h:outputText value="#{msg['person.name']}"/>
			</f:facet>
			<h:outputText id="name"
				value="#{person.lastName} #{person.firstName}"/>
		</rich:column>
		<rich:column>
			<f:facet name="header">
				<h:outputText value="#{msg['person.phone']}"/>
			</f:facet>
			<h:outputText id="phone" value="#{person.phone}"/>
		</rich:column>
	</rich:dataTable>
</a4j:form>
