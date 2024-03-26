<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="text"/>

  <xsl:template match="/">
    <xsl:apply-templates select="model"/>
  </xsl:template>

  <xsl:template match="model">
    <xsl:apply-templates select="model-name"/>
    <xsl:apply-templates select="column"/>
    <xsl:text>&#10;</xsl:text>
  </xsl:template>
  
  <xsl:template match="column">
    <xsl:value-of select="concat(',', normalize-space(column-value))"/>
  </xsl:template>
</xsl:stylesheet>