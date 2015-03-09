CHREST Netlogo Extension Documentation
======================================

Primitives Available
--------------------

| Primitive Name 	| Description |
| ---------------	| ----------- |
| associate-patterns | Associates two patterns of any modality together accordingly. |
| create-item-on-square-pattern | Creates an "ItemOnSquare" object from a string and two integers and  reports this object's string representation.	|
| create-number-pattern | Creates a "NumberPattern" object from an integer and reports this object's string representation.	|
| get-add-link-time	| Reports the value that the calling turtle's "_addLinkTime" variable is set to in its CHREST instance. |
| get-attention-clock | Reports the calling turtle's CHREST "_attentionClock" value. |
| get-current-scene | Reports a Netlogo list string representation of the calling turtle's "current-scene" turtle variable. |
| get-discrimination-time | Reports the value that the calling turtle's "_discriminationTime" variable is set to in its CHREST instance. |
| get-familiarisation-time | Reports the value that the calling turtle's "_familiarisationTime" variable is set to in its CHREST instance. |
| get-learning-clock | Reports the calling turtle's CHREST "_learningClock" value. |
| get-ltm-modality-avg-depth | Reports the average depth of nodes in the the long-term memory modality specified for the calling turtle. |
| get-ltm-modality-num-action-links	| Reports the cumulative number of action links in the long-term memory modality specified for the calling turtle. |
| get-ltm-modality-size	| Reports the number of nodes contained in the long-term memory modality specified for the calling turtle. |
| get-stm-contents-by-modality | Reports the contents of a specified short-term memory modality as a Netlogo list for the calling turtle. |
| get-stm-modality-size | Reports the number of nodes contained in the short-term memory modality specified for the calling turtle. |
| instantiate-chrest-in-turtle | Endows the calling turtle with its own instance of the CHREST architecture. |
| learn-current-scene | Learns the scene that is currently set to the calling turtle's "current-scene" variable using CHREST perception methods. |
| recognise-and-learn-pattern | Allows the calling turtle to discriminate or familiarise a pattern of a specified modality and pattern type. |
| recognise-pattern-and-return-patterns-of-specified-modality | Discriminates or familiarises a pattern and reports any patterns of a specified modality that the first pattern is associated with as a Netlogo list.												|
| reinforce-action-link | Enables a calling turtle to reinforce a link from the pattern specified to the action pattern specified by a given number. |
| save-ltm-network-image | Saves an image of the calling turtle's long-term memory to the directory specified on the user's system. |
| set-add-link-time | Sets the value that the calling turtle's "_addLinkTime" variable should be set to in its CHREST instance (milliseconds). |
| set-current-scene | Creates a new jchrest.lib.Scene instance and sets it to the calling turtle's "current-scene" turtle variable so that the turtle's CHREST instance can work with it. |
| set-discrimination-time | Sets the value that the calling turtle's "_discriminationTime" variable should be set to in its CHREST instance (milliseconds).|
| set-domain | Sets the current domain of the CHREST model associated with the calling turtle. |
| set-familiarisation-time | Sets the value that the calling turtle's "_familiarisationTime" variable should be set to in its CHREST instance (milliseconds). |


Netlogo Models Using the CHREST Extension
-----------------------------------------

Please add a link to your model here if it is not already listed!

  * [CHREST Tileworld](https://github.com/mlk5060/chrest-tileworld-netlogo-model)

Creating Turtles endowed with CHREST in a Netlogo Model
-------------------------------------------------------

To use the CHREST extension in your Netlogo model follow the steps listed below:

  1. Declare the following breed variables for the breed of turtles that are to be endowed with a 
     CHREST instance in the Netlogo model:
    1. "chrest-instance": Used to store a turtle's CHREST instance.
    2. "sight-radius": Used to generate a jchrest.lib.Scene instance when the "set-current-scene" 
       primitive is used.
    3. "current-scene": Used to store jchrest.lib.Scene instances.
  2. Have all turtles of the breed that are to be endowed with an instance of the CHREST architecture
     call the following extension primitives in the order specified:
    1. "instantiate-chrest-in-turtle": will initialise a CHREST instance for the calling turtle.
    2. "set-domain": will set the domain for the calling turtle's CHREST model so that CHREST's perceptual
       mechanisms can be utilised correctly in the model domain.  Note: these domains are maintained by
       the CHREST development team and are subject to change.  A list of all known domains supported by
       CHREST can be found [here](https://github.com/mlk5060/chrest/tree/master/src/main/java/jchrest/lib)
       (domains are postfixed with "Domain.java").  If your domain is not supported, please follow the
       instructions for adding support [here](https://github.com/mlk5060/chrest/blob/master/README.md).
