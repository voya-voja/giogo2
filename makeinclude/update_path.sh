function executeSed
{
	jfileList=`ls *.java`
	for jfile in $jfileList; do
		echo $jfile
		cp $jfile $jfile.tmp
		sed -f $MAC/makeinclude/update.paths $jfile.tmp >$jfile
		rm $jfile.tmp
	done
};

function walkTree
{
	fileList=`ls`
	for file in $fileList; do
		if [ $file -a -d $file ]; then
			cd $file
			echo "Updating $PWD ..."
			walkTree
			executeSed
			cd ..
		fi
	done
};

echo "Update $PWD"
walkTree 
