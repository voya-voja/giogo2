#!/bin/sh

java -Djava.rmi.server.codebase=http://pippin.inexum.com/classes/ -Djava.rmi.server.hostname=pippin.inexum.com \
com.inexum.SampleMPayWallet.FoldingWallet

