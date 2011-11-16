<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml"/>
	<xsl:variable name="longitudinal">
		<xsl:text>ru.sstu.sm.longitudinal.gui.ModuleImpl</xsl:text>
	</xsl:variable>
	<xsl:variable name="torsion">
		<xsl:text>ru.sstu.sm.torsion.gui.ModuleImpl</xsl:text>
	</xsl:variable>
	<xsl:variable name="cross">
		<xsl:text>ru.sstu.sm.cross.gui.ModuleImpl</xsl:text>
	</xsl:variable>
	<xsl:template match="Config">
		<xsl:element name="config">
			<xsl:attribute name="version">
				<xsl:text>3.0</xsl:text>
			</xsl:attribute>
			<xsl:attribute name="module">
				<xsl:choose>
					<xsl:when test="@module = $longitudinal">
						<xsl:text>sm-longitudinal</xsl:text>
					</xsl:when>
					<xsl:when test="@module = $torsion">
						<xsl:text>sm-torsion</xsl:text>
					</xsl:when>
					<xsl:when test="@module = $cross">
						<xsl:text>sm-cross</xsl:text>
					</xsl:when>
				</xsl:choose>
			</xsl:attribute>
			<xsl:element name="direction">
				<xsl:choose>
					<xsl:when test="@direction">
						<xsl:value-of select="@direction"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>RIGHT</xsl:text>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:element>
			<xsl:element name="type">
				<xsl:choose>
					<xsl:when test="@type">
						<xsl:value-of select="@type"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>FREE</xsl:text>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:element>
			<xsl:if test="@delta">
				<xsl:element name="delta">
					<xsl:attribute name="unit">
						<xsl:text>m</xsl:text>
					</xsl:attribute>
					<xsl:attribute name="value">
						<xsl:value-of select="@delta"/>
					</xsl:attribute>
				</xsl:element>
			</xsl:if>
			<xsl:if test="@limit">
				<xsl:element name="limit">
					<xsl:attribute name="unit">
						<xsl:text>MPa</xsl:text>
					</xsl:attribute>
					<xsl:attribute name="value">
						<xsl:value-of select="@limit"/>
					</xsl:attribute>
				</xsl:element>
			</xsl:if>
			<xsl:element name="sections">
				<xsl:apply-templates select="Section"/>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<xsl:template match="Section">
		<xsl:choose>
			<xsl:when test="../@module = $longitudinal">
				<xsl:element name="section">
					<xsl:apply-templates/>
				</xsl:element>
			</xsl:when>
			<xsl:when test="../@module = $torsion">
				<xsl:choose>
					<xsl:when test="@type = 'annular'">
						<xsl:element name="annularSection">
							<xsl:apply-templates/>
						</xsl:element>
					</xsl:when>
					<xsl:when test="@type = 'circular'">
						<xsl:element name="circularSection">
							<xsl:apply-templates/>
						</xsl:element>
					</xsl:when>
					<xsl:when test="@type = 'rectangular'">
						<xsl:element name="rectangularSection">
							<xsl:apply-templates/>
						</xsl:element>
					</xsl:when>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="../@module = $cross">
				<xsl:element name="section">
					<xsl:attribute name="joint">
						<xsl:choose>
							<xsl:when test="@type = 'joint'">
								<xsl:text>true</xsl:text>
							</xsl:when>
							<xsl:otherwise>
								<xsl:text>false</xsl:text>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:attribute>
					<xsl:element name="young">
						<xsl:attribute name="unit">
							<xsl:text>MPa</xsl:text>
						</xsl:attribute>
						<xsl:attribute name="value">
							<xsl:text>2e5</xsl:text>
						</xsl:attribute>
					</xsl:element>
					<xsl:element name="inertiaMoment">
						<xsl:attribute name="unit">
							<xsl:text>m4/1000000</xsl:text>
						</xsl:attribute>
						<xsl:attribute name="value">
							<xsl:text>1.0</xsl:text>
						</xsl:attribute>
					</xsl:element>
					<xsl:apply-templates/>
				</xsl:element>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="Length">
		<xsl:element name="length">
			<xsl:attribute name="unit">
				<xsl:value-of select="@unit"/>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="Young">
		<xsl:element name="young">
			<xsl:attribute name="unit">
				<xsl:value-of select="@unit"/>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="CrossSection">
		<xsl:element name="crossSection">
			<xsl:attribute name="unit">
				<xsl:choose>
					<xsl:when test="@unit = 'cm2'">
						<xsl:text>m2/10000</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="@unit"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="Load">
		<xsl:element name="load">
			<xsl:attribute name="unit">
				<xsl:value-of select="@unit"/>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="Moment">
		<xsl:element name="torque">
			<xsl:attribute name="unit">
				<xsl:choose>
					<xsl:when test="@unit = 'kN m'">
						<xsl:text>(N.m).1000</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="@unit"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="DistributedLoad">
		<xsl:element name="distributedLoad">
			<xsl:attribute name="unit">
				<xsl:value-of select="@unit"/>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="Diameter">
		<xsl:element name="diameter">
			<xsl:attribute name="unit">
				<xsl:value-of select="@unit"/>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="External">
		<xsl:element name="externalDiameter">
			<xsl:attribute name="unit">
				<xsl:value-of select="@unit"/>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="Internal">
		<xsl:element name="internalDiameter">
			<xsl:attribute name="unit">
				<xsl:value-of select="@unit"/>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="Width">
		<xsl:element name="biggerSize">
			<xsl:attribute name="unit">
				<xsl:value-of select="@unit"/>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template match="Height">
		<xsl:element name="smallerSize">
			<xsl:attribute name="unit">
				<xsl:value-of select="@unit"/>
			</xsl:attribute>
			<xsl:attribute name="value">
				<xsl:value-of select="."/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
