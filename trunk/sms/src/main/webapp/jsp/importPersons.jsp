<!------------------------ Delete lab panel -------------------------------->
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="a4j" uri="http://richfaces.org/a4j" %>
<%@ taglib prefix="rich" uri="http://richfaces.org/rich" %>
<rich:modalPanel id="importPersons" height="180" width="400">
	<f:facet name="header">
		<h:outputFormat value="#{msg['tab.persons']}"/>
	</f:facet>
	<f:facet name="controls">
		<h:graphicImage value="../images/delete.png" style="cursor:pointer"
			onclick="Richfaces.hideModalPanel('importPersons')"/>
	</f:facet>
	<a4j:form id="importForm">
		<h:panelGrid columns="1" width="100%">
			<h:selectOneMenu value="#{persons.format}" immediate="true">
				<f:selectItems value="#{persons.formats}"/>
				<a4j:support event="onchange" reRender="upload"/>
			</h:selectOneMenu>
			<rich:fileUpload id="upload"
				acceptedTypes="#{persons.format.types}"
				fileUploadListener="#{persons.importPersons}"
				listWidth="100%" listHeight="58" ajaxSingle="true"
				addControlLabel="#{msg['action.add']}"
				uploadControlLabel="#{msg['action.upload']}"
				cancelEntryControlLabel="#{msg['action.cancel']}"
				clearControlLabel="#{msg['action.clear']}" 
				clearAllControlLabel="#{msg['action.clearall']}" 
				transferErrorLabel="#{msg['error.file.format']}"
				stopControlLabel="#{msg['action.stop']}"
				doneLabel="#{msg['common.done']}"
				maxFilesQuantity="1" immediateUpload="true">
				<a4j:support event="onuploadcomplete"
					oncomplete="Richfaces.hideModalPanel('importPersons')"
					reRender="labsListTable"/>
			</rich:fileUpload>
			<%--
			 --%>
		</h:panelGrid>
	</a4j:form>
</rich:modalPanel>
