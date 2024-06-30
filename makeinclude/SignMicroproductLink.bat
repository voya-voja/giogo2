@echo off
if not exist MicroproductLink.jar     goto microproductLinkMissing

if not exist ..\.keystore   goto keystoreMissing

echo Signing MicroproductLink...
jarsigner -keystore ../.keystore -storepass inexum -signedjar MicroproductLinkSigned.jar MicroproductLink.jar server_key
goto end

:microproductLinkMissing
    echo MicroproductLink.jar is missing!
    goto end
:keystoreMissing
    echo ..\.keystore is missing!
:end

