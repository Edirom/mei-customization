#!/bin/bash

VERSION_STYLESHEET=latest
VERSION_ODD=latest
VERSION_ENCODING_TOOLS=latest
VERSION_W3C_MUSICXML=latest
VERSION_MEILER=latest

MEI_SOURCES_HOME=/usr/share/xml/mei
TEI_SOURCES_HOME=/usr/share/xml/tei

sudo apt-get update
sudo apt-get install libxml2-utils

#https://github.com/TEIC/Stylesheets/releases/latest
if [ "$VERSION_STYLESHEET" = "latest" ] ; then
VERSION_STYLESHEET=$(curl "https://api.github.com/repos/TEIC/Stylesheets/releases/latest" | grep -Po '"tag_name": "v\K.*?(?=")');  
    fi
echo "Stylesheet version set to ${VERSION_STYLESHEET}"
# download the required tei odd and stylesheet sources in the image and move them to the respective folders (${TEI_SOURCES_HOME})
curl -s -L -o /tmp/stylesheet.zip https://github.com/TEIC/Stylesheets/releases/download/v${VERSION_STYLESHEET}/tei-xsl-${VERSION_STYLESHEET}.zip
unzip /tmp/stylesheet.zip -d /tmp/stylesheet
rm /tmp/stylesheet.zip
mkdir -p  ${TEI_SOURCES_HOME}/stylesheet
cp -r /tmp/stylesheet/xml/tei/stylesheet/*  ${TEI_SOURCES_HOME}/stylesheet
rm -r /tmp/stylesheet

#https://github.com/music-encoding/music-encoding - todo sort each version into correct folder, no version applicable yet
# download the required tei odd and stylesheet sources in the image and move them to the respective folders (${TEI_SOURCES_HOME})
curl -s -L -o /tmp/mei200.zip https://github.com/music-encoding/music-encoding/archive/refs/tags/MEI2012_v2.0.0.zip
unzip /tmp/mei200.zip -d /tmp/mei200
rm /tmp/mei200.zip
mkdir -p  ${MEI_SOURCES_HOME}/music-encoding/mei200
cp -r /tmp/mei200/*/*  ${MEI_SOURCES_HOME}/music-encoding/mei200
rm -r /tmp/mei200
xmllint -xinclude ${MEI_SOURCES_HOME}/music-encoding/mei200/source/specs/mei-source.xml -o ${MEI_SOURCES_HOME}/music-encoding/mei200/source/mei-source_canonicalized.xml
curl -s -L -o /tmp/mei211.zip https://github.com/music-encoding/music-encoding/archive/refs/tags/MEI2013_v2.1.1.zip
unzip /tmp/mei211.zip -d /tmp/mei211
rm /tmp/mei211.zip
mkdir -p  ${MEI_SOURCES_HOME}/music-encoding/mei211
cp -r /tmp/mei211/*/*  ${MEI_SOURCES_HOME}/music-encoding/mei211
rm -r /tmp/mei211
xmllint -xinclude ${MEI_SOURCES_HOME}/music-encoding/mei211/source/specs/mei-source.xml -o ${MEI_SOURCES_HOME}/music-encoding/mei211/source/mei-source_canonicalized.xml
curl -s -L -o /tmp/mei300.zip https://github.com/music-encoding/music-encoding/archive/refs/tags/v3.0.0.zip
unzip /tmp/mei300.zip -d /tmp/mei300
rm /tmp/mei300.zip
mkdir -p  ${MEI_SOURCES_HOME}/music-encoding/mei300
cp -r /tmp/mei300/*/*  ${MEI_SOURCES_HOME}/music-encoding/mei300
rm -r /tmp/mei300
xmllint -xinclude ${MEI_SOURCES_HOME}/music-encoding/mei300/source/specs/mei-source.xml -o ${MEI_SOURCES_HOME}/music-encoding/mei300/source/mei-source_canonicalized.xml
curl -s -L -o /tmp/mei401.zip https://github.com/music-encoding/music-encoding/archive/refs/tags/v4.0.1.zip
unzip /tmp/mei401.zip -d /tmp/mei401
rm /tmp/mei401.zip
mkdir -p  ${MEI_SOURCES_HOME}/music-encoding/mei401
cp -r /tmp/mei401/*/*  ${MEI_SOURCES_HOME}/music-encoding/mei401
rm -r /tmp/mei401
xmllint -xinclude ${MEI_SOURCES_HOME}/music-encoding/mei401/source/mei-source.xml -o ${MEI_SOURCES_HOME}/music-encoding/mei401/source/mei-source_canonicalized.xml
mkdir -p  ${MEI_SOURCES_HOME}/music-encoding/meidev
git clone --depth 1 https://github.com/music-encoding/schema /tmp/meidev 
cd /tmp/meidev
git rev-parse HEAD > dev/GITHASH
cp -r /tmp/meidev/dev ${MEI_SOURCES_HOME}/music-encoding/meidev
mkdir -p  ${MEI_SOURCES_HOME}/music-encoding/meidev/source
mv ${MEI_SOURCES_HOME}/music-encoding/meidev/mei-source_canonicalized.xml ${MEI_SOURCES_HOME}/music-encoding/meidev/source/mei-source_canonicalized.xml
rm -r /tmp/meidev
