======================================
CHREST Netlogo Extension Documentation
======================================

--------------------
Primitives Available
--------------------

Primitive Name					Description
--------------					-----------
setup-chrest-extension: 		Sets global variables that need to be declared in the Netlogo model
								so that the CHREST architecture can be used in the current Netlogo
								model.
instantiate-chrest-in-agent:	Endows a turtle with its own instance of the CHREST architecture.
recognise-and-learn-percept:	Enables a turtle to recognise and learn a percept given visual field
								dimensions.
recognise-and-learn-action: 	Enables a turtle to recognise and learn an action.

------------------------------------------------
Netlogo Models that Support the CHREST Extension
------------------------------------------------

- ChrestTileworld.nlogo

-----------------------------------------------------------------
Configuring Extension to Work with Unsupported or Modified Models
-----------------------------------------------------------------

If the model you are looking to use the CHREST extension in is not currently supported
or elements of it are not supported i.e. breed types are not accounted for when creating
list patterns, you need to add relevant code to the "NetlogoChrestInputOutputInterface.java" 
file.

-------------------------------------------------------
Creating Turtles endowed with CHREST in a Netlogo Model
-------------------------------------------------------

To use the CHREST extension in your Netlogo model follow the steps listed below:

1) Ensure that the Netlogo model that the extension is to be used in actually supports the extension.
   Different Netlogo models require different information to be passed to CHREST so that the 
   architecture can operate correctly in the Netlogo model environment.
2) The filename of your Netlogo model should match the filenames given in the "Netlogo Models that 
   Support the CHREST Extension" section above.  If not, the extension will be unable to 
   provide an interface between your Netlogo model and the CHREST architecture.
3) In your Netlogo model, ensure that you have done the following:
	a) Declared a global variable called "netlogo-model".  This variable will store the filename of
	   the Netlogo model that the CHREST extension is being used in so that model-specific 
	   methods are used.
	b) Declared a breed variable called "chrest-instance" for the breed of turtles that are to 
	   be endowed with a CHREST instance in the Netlogo model.
	d) Called the "chrest:setup-chrest-extension" primitive when the Netlogo model is set-up.
	c) Called the "chrest:instantiate-chrest-in-agent" primitive when the Netlogo model is being 
	   set-up.
