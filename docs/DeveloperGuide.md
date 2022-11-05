# Developer Guide

## Table of Contents
* [Design & Implementation](#design--implementation)
  * [Main Mode](#main-mode)
    * [Build Manager](#build-manager) 
  * [Edit Mode](#edit-mode)
    * [Build](#build)
    * [Components](#components)
  * [Storage](#storage)
  * [Export](#export)

* [Appendix](#appendix)
  * [Product Scope](#product-scope)
  * [Target User Profile](#target-user-profile)
  * [Value Proposition](#value-proposition)
  * [User Stories](#user-stories)
  * [Non-Functional Requirements](#non-functional-requirements)
  * [Glossary](#glossary)
* [Instructions for Manual Testing](#instructions-for-manual-testing)

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Main Mode

This section describes the implementation of Main Mode features.

Once the `main()` method of ComputerComponentChooser is called, instances for the `BuildManager`, `Parser`, 
`editParser`, `Storage`, `Ui` classes are initialized.

#### BuildManager

![](/images/BuildManager.png)

The builds are managed by the BuildManager class. It contains a list of builds in a HashMap. The class also contains
methods to find and filter builds based on user requirements. The BuildManager class is a singleton class, meaning that
there is only one instance of the class in the program. This is to ensure that there is only one list of builds in the
program. 

In our application, BuildManager is a class object that contains a HashMap of builds. The HashMap is used to store the
builds in the program. The key of the HashMap is the name of the build, and the value is the build object.

The BuildManager class is responsible for the following operations:
- Add a build to the list of builds
- Delete a build from the list of builds
- Get a particular build from the list of builds
- Get the list of builds
- Find build that contains a search term from the list of builds
- Filter builds based on user requirements

##### Add a build to the list of builds

This feature allows users to add a build to the list.

When the user first inputs a command for the adding of a build, the `Parser` class will parse the command and call the
method `mainParseAdd()` in the Parser Class. 

The 'mainParseAdd() will check if the provided name is valid or is blank or made up of white spaces. If the provided 
name is valid, the method will create a build with the provided name and the method will call the method `addBuild()` 
in the `BuildManager` class, passing `addBuild` the created build object with the provided name. If the provided name is 
empty or made up of whitespaces, the `BlankStringException` exception will be thrown and an error message will be printed.

The `addBuild()` method will check if the provided name is already in the list of builds. If the provided name is not
in the list of builds, the method will add the build into the list of builds. If the provided name is already in the 
list of builds, the `DuplicateBuildException` exception will be thrown and an error message will be printed.

After adding the build into the list, the program will return to the `mainParseAdd` method where the method will print
a message, telling the user the build has been added.

Finally, the `mainParseAdd` method will call the `saveBuilds()` method in the `Storage` class to save the list of builds
into the data file.

The following sequence diagram shows how the add build operation works:
![](/images/BuildManagerAddBuildSequence.png)

##### Listing all builds

This features allow users to list all builds. 

When the user first inputs a command for the listing of all builds, the `Parser` class will parse the command and call
the method `mainParseList()` in the Parser Class. 

The `mainParseList()` method will then print the list of builds. If you have no builds, the method will print a message
telling you "You have no builds".

The following sequence diagram shows how the list operation works:
![](/images/BuildManagerListBuildSequence.png)

##### Finding a build

This features allows users to find builds that contain a search term.

When the user first inputs a command for the finding of a build, the `Parser` class will parse the command and call the
method `mainParseFind()` in the Parser Class. 

The `mainParseFind()` method will then call the method `findBuild()` in the `BuildManager` class, passing `findBuild()`
the search term. The `findBuild()` method will then search the list of builds for builds that contain the search term.
If there are builds that contain the search term, the method will print the list of builds that contain the search term.
If there are no builds that contain the search term, the method will print a message telling you that "No builds that 
meet specifications found."

The following sequence diagram shows how the find operation works:
![](/images/BuildManagerFindBuildSequence.png)

##### Filtering builds based on user requirements

This feature allows users to filter builds based on user requirements. 

When the user first inputs a command for the filtering of builds, the `Parser` class will parse the command and call
the method `mainParseFilter()` in the Parser Class.

The `mainParseFilter()` method will then call the `filterBuilds()` method in the `BuildManager` class. Based on the user
inputted type of filter, the `filterBuilds()` method will call the appropriate method to filter the
builds.

Case 1: If the user inputs the filter type `price`, the `filterBuilds()` method will call the `filterPrice()` method in
the `BuildManager` class. The `filterPrice()` method will then filter the builds and get builds within the price range
provided by the user. The `printFilteredList()` method will then print the list of builds within the price range.

Case 2: If the user inputs the filter type `power`, the `filterBuilds()` method will call the `filterPower()` method in
the `BuildManager` class. The `filterPower()` method will then filter the builds and get builds within the power range
provided by the user. The `printFilteredList()` method will then print the list of builds within the power range.

Case 3: If the user inputs the filter type `compatibility`, the `filterBuilds()` method will call the 
`filterCompatibility()` method in the `BuildManager` class. The `filterCompatibility()` method will then filter the 
builds and get builds that pass all the compatibility checks. The `printFilteredList()` method will then print the
list of builds that pass all the compatibility checks.

The following sequence diagram shows how the filter operation works:
![](/images/BuildManagerFilterSequence.png)

### Edit Mode

#### Build 

![](/images/Build.png)

A build is a collection of components that are used to create a PC. A build can be saved and loaded from the storage. 
The user can also export the build to a text file via the export functionality. 

In our application build is a class object which contains a 2D linked hash map of components. LinkedHashMap2D is a class
representing a 2D linked hash map data structure. The keys of the outer map are the component types and the keys of the inner
are the component names. The values of the inner map are the components themselves.

The user can add, delete, and view the components in the build. The user can also
perform aggregate operations on the build such as viewing the total price of the build and viewing the total wattage of the build.
There are also check compatibility functions that check if the build's components are compatible with each other in different ways. Such as:
- Checking if the power supply is compatible with the build's total power consumption
- Checking if the build's motherboard is compatible with the build's CPU and Cooler
- Checking if the form factor of the build's case is compatible with the build's motherboard
- Checking if the build's storage is compatible with the case expansion slots
- Checking if the number of the build's GPUs is compatible with the motherboard GPU slots
- Checking if the number of the build's RAM is compatible with the motherboard RAM slots

##### Add & delete component

The user can add and delete components from the build. When the user adds a component, the component is added to the 2D linked hash map. 
When the user deletes a component, the component is removed from the 2D linked hash map. This is done by calling the `addComponent()` and `deleteComponent()` methods of the Build class.
Which in turn calls the `addElement()` and `removeElement()` methods of the LinkedHashMap2D class.

![](/images/BuildSequence.png)

### Storage

![](/images/Storage.png)

Note: Some methods are being left out to show the core functionality of the storage class.

Storage is used for loading and saving the user's builds. The user can save their builds to a text file and load them 
from a text file. 

The `Storage` class has private attributes `FILE_DIRECTORY`, `BUILD_FILE_PATH` and `COMPONENT_FILE_PATH`. 
`FILE_DIRECTORY` is the directory where the text files are stored. 
`BUILD_FILE_PATH` is the path to the text file where all the build names are stored. 
`COMPONENT_FILE_PATH` is the path to the text file where the file is named after the build name. The components of the build 
are stored in their respective build text file.

The `Storage` class has a constructor that takes in a `BuildManager` object. The `BuildManager` object is used to access the
builds in the `BuildManager` object. The `Storage` class has a `loadBuild()` method that loads the builds from the text file
a `saveBuild()` method that saves the builds from the `BuildManager` object into the text file. The `Storage` class also has
a `loadComponent()` and `saveComponent()` methods utilize the `BuildManager` object to access the `Build` object to load and save
the components of the build into their respective text files. The `deleteBuild()` method deletes the build from the text file with the path
`BUILD_FILE_PATH` and deletes the text file with the path `COMPONENT_FILE_PATH` that is named after the build name.


#### Load

![](images/StorageLoadSequence.png)

The `loadBuild()` method is called when the program starts. The `loadBuild()` method reads the text file with the path `BUILD_FILE_PATH` and
creates a `Build` object for each build name in the text file. If there are duplicate build names in the text file or the text file does not exist,
respective error messages will be printed out. The `loadComponent()` method is called for each build name in the text file. The `loadComponent()` method
reads the text file with the path `COMPONENT_FILE_PATH` and creates a `Component` object for each component in the text file. These `Component` objects
are then added to the `Build` object. If the text file does not exist, an error message will be printed out.

#### Save

Save is split into two parts, saving the build names and saving the components of the build.

![](images/StorageSaveBuildSequence.png)

The `saveBuild()` method is called when the user adds a build. The `saveBuild()` method writes the build names in the `BuildManager` object
into the text file with the path `BUILD_FILE_PATH`. Same process applies to the method `deleteBuild()` when the user deletes a build.

![](images/StorageSaveComponentSequence.png)

The `saveComponent()` method is called when the user adds or delete a component to a build. The `saveComponent()` method writes the components of the build
into the text file with the path `COMPONENT_FILE_PATH`. If the text file does not exist, the `saveComponent()` method will create a new text file with the path `COMPONENT_FILE_PATH`.



### Components

![](/images/Component.png)

Each of the various components are represented by classes stored in the `components` package. The current components represented are the following:
- `Cpu`
- `Gpu`
- `Motherboard`
- `Memory`
- `Drive`
- `Case`
- `Cooler`
- `PowerSupply`
- `Monitor`

An `Other` class is also included to represent any other components that are not currently represented in the application. All the classes inherit from the `Component` class, and any new components added should also inherit from the `Component` class.
All components have the attributes `name`, `price` and `power`, which represent the name of the component, the price of the component and the power consumption of the component respectively. These attributes hold the same meaning in all subclasses except for the `PowerSupply` class, where the `power` attribute represents the power output of the power supply instead.

In addition to the getters and setters for each of the class fields, the classes share the following methods:
- `toCsv()` - returns a string representation of the component in CSV format
- `saveAsString()` - returns a string representation of the component in a format that can be saved to a text file
- `getDetails()` - returns a string representation of the component in a format that can be displayed to the user
- `getType()` - returns the type of the component

### Export

In our application export is a utility class. The user can export all builds or a specific build to a text file. The user can also export
all builds to a CSV file. 


## Product scope
### Target user profile

This product is targeted towards PC building enthusiasts and commercial custom PC builders who have a need and want to 
keep track of their PC builds. It is optimized for users to work with a Command Line Interface (CLI).

### Value proposition

This product helps builders to keep track of their PC builds and their components. It also helps them to keep track of
their total power consumption and the total cost of their builds. Compatibility of components is also checked to ensure
that the build is able to function properly..

## User Stories
The following user stories will be implemented in the version stated in the table and will be available as a feature
from that version onwards.

| Version | As a ... | I want to ...              | So that I can ...                                           |
|---------|----------|----------------------------|-------------------------------------------------------------|
 | v1.0    | user     | add a new build            | refer to when I want to track my build                      |
| v1.0    | user     | list all builds            | get a list of all builds in one place                       |
| v1.0    | user     | delete a build             | remove builds that I do not need anymore                    |
| v2.0    | user     | find a to-do item by name  | locate a to-do without having to go through the entire list |
| v2.0    | user     | add a component to a build | refer to when I want to track a component in my build       |
| v2.0    | user     | list all components        | get a list of all components of a build in one place        |
| v2.0    | user     | delete a component         | remove components that I do need anymore                    |
| v2.0    | user     | delete a component         | remove components that have mistakes                        |
| v2.0    | user     | check a build              | check the compatability of all components of a build        |
| v2.0    | user     | info                       | view the relevant information about a build                 | 
 | v2.0    | user     | export a build             | export a build to a text file                               |
 | v2.0    | user     | export a build             | export a build to a csv file                                |
 | v2.0    | user     | find a build               | locate a build without having to go through the entire list |
 | v2.0    | user     | filter builds              | find all builds that are within a certain price range       |
| v2.0    | user     | filter builds              | find all builds that are within a certain power range       |
| v2.0    | user     | filter builds              | find all builds that are compatible                         |

## Non-Functional Requirements

Product should work on any mainstream OS as long as it has Java 11 or above installed.

## Glossary

* *glossary item* - Definition
* 
| Terms       | Definition                                                                                         |
|-------------|----------------------------------------------------------------------------------------------------|
| CPU         | The component of a computer system that controls the interpretation and execution of instructions. |
| GPU         | Graphics processing unit, a specialized processor designed to accelerate graphics rendering.       |
| Drive       | Storage devices to store user information.                                                         |
| Memory      | A computer's short-term memory, where the data that the processor is currently using is stored.    |
| Motherboard | The main circuit board within a computer that the other components plug into to create a whole.    |
| Powersupply | A power supply is a hardware component that supplies power to the computer.                        |

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
