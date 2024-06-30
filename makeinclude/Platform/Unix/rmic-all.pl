#!/usr/bin/perl

my @files;
if (-d $ARGV[0])
{
    @files = `find $ARGV[0] -name '*.rmi'`;
}
else
{
    @files = `find . -name '*.rmi'`;
}

foreach (@files)
{
    s|^\./||;
    s/\.rmi$//;
    s|/|.|g;
    print `rmic $_`;
}

