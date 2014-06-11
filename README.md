CHREST Netlogo Extension Documentation
======================================

Primitives Available
--------------------

| Primitive Name												| Description														|
| -------------------------------------------------------------	| ----------------------------------------------------------------- |	
| associate-patterns											| Associates two patterns of any modality together accordingly.		|
| create-item-on-square-pattern 								| Creates an "ItemOnSquare" object from a string and two integers 	|
|																| and reports this object's string representation.					|
| create-number-pattern         								| Creates a "NumberPattern" object from an integer and reports this |
|																| object's string representation.									|
| get-add-link-time												| Reports the value that the calling turtle's "_addLinkTime" 		|
|																| variable is set to in its CHREST instance. 						|
| get-chrest-clock												| Reports the value that the calling turtle's "_clock" variable     |
| 																| is set to in its CHREST instance.									|
| get-discrimination-time       								| Reports the value that the calling turtle's "_discriminationTime" |
|																| variable is set to in its CHREST instance.						|
| get-familiarisation-time 										| Reports the value that the calling turtle's "_familiarisationTime"|
|																| variable is set to in its CHREST instance.						|
| get-ltm-modality-avg-depth									| Reports the average depth of nodes in the the long-term memory 	|
| 																| modality specified for the calling turtle.						|
| get-ltm-modality-num-action-links								| Reports the cumulative number of action links in the long-term 	|
|																| memory modality specified for the calling turtle.					|
| get-ltm-modality-size											| Reports the number of nodes contained in the long-term memory 	|
|																| modality specified for the calling turtle.						|
| get-stm-contents-by-modality									| Reports the contents of a specified short-term memory modality as |
|																| a Netlogo list for the calling turtle.							|
| get-stm-modality-size											| Reports the number of nodes contained in the short-term memory 	|
|																| modality specified for the calling turtle.						|
| instantiate-chrest-in-turtle									| Endows the calling turtle with its own instance of the CHREST 	|
|																| architecture.														|
| recognise-and-learn-pattern									| Allows the calling turtle to discriminate or familiarise a 		|
| 																| pattern of a specified modality and pattern type.					|
| recognise-pattern-and-return-patterns-of-specified-modality	| Discriminates or familiarises a pattern and reports any patterns 	| 
|																| of a specified modality that the first pattern is associated with |
|																| as a Netlogo list.												|
| reinforce-action-links										| Enables a calling turtle to reinforce a link from the pattern  	|
|																| specified to the action pattern specified by a given number.		|
| save-ltm-network-image										| Saves an image of the calling turtle's long-term memory to the 	|
|																| directory specified on the user's system.							|
| set-add-link-time												| Sets the value that the calling turtle's "_addLinkTime" variable 	|
|																| should be set to in its CHREST instance (milliseconds). 			|
| set-discrimination-time										| Sets the value that the calling turtle's "_discriminationTime" 	|
|																| variable should be set to in its CHREST instance (milliseconds). 	|
| set-familiarisation-time										| Sets the value that the calling turtle's "_familiarisationTime" 	|
|																| variable should be set to in its CHREST instance (milliseconds). 	|


Netlogo Models Using the CHREST Extension
-----------------------------------------

Please add a link to your model here if it is not already listed!

  * [CHREST Tileworld](https://github.com/mlk5060/chrest-tileworld-netlogo-model)

Creating Turtles endowed with CHREST in a Netlogo Model
-------------------------------------------------------

To use the CHREST extension in your Netlogo model follow the steps listed below:

  1. Declare a breed variable called "chrest-instance" for the breed of turtles that are to be endowed with a 
     CHREST instance in the Netlogo model.
  2. Call the "chrest:instantiate-chrest-in-turtle" primitive on all turtles of the breed that are to be 
     endowed with an instance of the CHREST architecture.
