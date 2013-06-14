AC_PREREQ(2.57)
AC_INIT(sipXsummary, 4.6.0, spaghetty_AT_gmail.com)
AC_CONFIG_AUX_DIR(config)
m4_include([config/general.m4])
m4_include([config/sipXlib.m4])
m4_include([config/golang.m4])

AM_INIT_AUTOMAKE(foreign tar-ustar)
SFAC_INIT_FLAGS
SFAC_LIB_COMMSERVER
SFAC_AUTOMAKE_VERSION([1.6])
CHECK_XARGS_REPLACE
CHECK_JDK
CHECK_CLOVER

m4_include([config/java2.m4])
m4_include([config/java.m4])
AC_PROG_JAVA_CC([javac])
AC_PROG_JAVA([java])
PROG_JAVA_DEP


AC_CONFIG_FILES([Makefile])
AC_CONFIG_FILES([config-plugin/Makefile])

AM_CONDITIONAL(IS_RPMBUILD, test x$enable_rpmbuild = xyes)

AC_OUTPUT