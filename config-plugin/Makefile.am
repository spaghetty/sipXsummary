include $(top_srcdir)/config/utility.am
include $(top_srcdir)/config/java.am
include $(top_srcdir)/common.am

SUBDIRS = \
	.

EXTRA_DIST = \
	$(sipXsummary_SRC) \
	$(sipXsummary_RESOURCES)

jardir = @SIPX_JAVADIR@/sipXconfig/plugins
JAR_FILE = sipXsummay-config.jar
jar_DATA = $(JAR_FILE)

sipXsummary_SRC = $(shell cd $(srcdir); find src -type f -name '*.java')
sipXsummary_RESOURCES = \
	$(shell cd $(srcdir); find plugin -type f) \
	sipxplugin.beans.xml


sipXsummary_DEPS = \
	$(call JavaDep,@SIPX_JAVADIR@/sipXcommons @SIPX_JAVADIR@/sipXconfig,$(sipXsummary_PKGS))

$(JAR_FILE) : javac-sipXsummary $(sipXsummary_RESOURCES)
	jar cf $@ \
	$(call JarInclude,$(JAVAROOT),.) \
	$(call JarInclude,$(srcdir),$(sipXsummary_RESOURCES))

# style checks java source code coding style agains proper sipxecs conforming. Not nec.
# but you will probably find very useful
style_SRC = $(sipXsummary_SRC)

precommit : style validate;

validate : sipxplugin.beans.xml
	xmllint --noout \
	--schema $(SIPX_DATADIR)/schema/spring-beans-2.0.xsd  \
	$^