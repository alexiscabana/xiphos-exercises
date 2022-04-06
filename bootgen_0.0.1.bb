SUMMARY = "Bitbake Xilinx bootgen 2019.2 recipe"
DESCRIPTION = "Recipe for cross-compiling and installing Xilinx bootgen onto the image"
PN = "bootgen"
PR = "r0"

# commit for the 2019.2 tag
SRCREV = "f9f477adf243fa40bc8c7316a7aac37a0efd426d"
SRC_URI = "git://github.com/Xilinx/bootgen;protocol=http;branch=master"
LICENSE = "Apache"
# checksum was processed manually
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=c979df673927004a489691fc457facff"
S = "${WORKDIR}/bootgen"

# bootgen depends on openssl
DEPENDS += "openssl"

EXTRA_OEMAKE += "'INCLUDE_USER=-I${S}/' \
                 'CROSS_COMPILER=${CXX}' \
                 'LIBS=${STAGING_LIBDIR}/libssl.a ${STAGING_LIBDIR}/libcrypto.a -ldl -lpthread' \
                 "

do_compile(){
	cd ${WORKDIR}/git/
    oe_runmake
}

do_install(){
    install -m 0755 -d ${D}${bindir}
    install -m 0644 ${WORKDIR}/git/bootgen ${D}${bindir}
}
