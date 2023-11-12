---
layout: default.md
title: "Developer Guide"
pageNav: 3
---

# MedBook Developer Guide

## Acknowledgements

This project has not used any third-party libraries.

---

## Setting up and Getting Started

For initial setup and getting started with the development, please refer to the guide: [_Setting up and getting started_](SettingUp.md).

---

## Design Overview

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" alt="Architecture Diagram"/>

The **_Architecture Diagram_** above provides a high-level design overview of the application.

Below is a quick overview of the main components and their interactions:

#### Main Components:

- **`Main`**: Responsible for initializing other components in the correct sequence upon application launch, and ensures proper shut down procedures are followed. It consists of [`Main`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/MainApp.java) classes.

- **`UI`** ([Details](#ui-component)): Manages the User Interface of the app.
- **`Logic`** ([Details](#logic-component)): Handles command execution.
- **`Model`** ([Details](#model-component)): Manages in-memory data.
- **`Storage`** ([Details](#storage-component)): Handles reading from and writing to the hard disk.

- **`Commons`**: A collection of classes used by multiple components.

#### Component Interactions:

The sequence diagram below shows the interactions between components for the `delete 1` command:

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" alt="Architecture Sequence Diagram" />

Each of the four main components:

- Defines its API in an interface named after the component.
- Implements its functionality using a `{Component Name}Manager` class, following the corresponding API interface.

For example, the `Logic` component's API is defined in `Logic.java`, and its functionality is implemented in `LogicManager.java`.

#### Detailed Component Descriptions:

[More details about each component are provided in the subsequent sections.](#detailed-component-descriptions)

---

### Detailed Component Descriptions

#### UI Component

- **API**: [`Ui.java`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)
- The UI component is responsible for handling all user interface operations.

<details>
<summary>Click to expand details</summary>

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI is composed of various components such as `MainWindow`, `CommandBox`, `ResultDisplay`, `PersonListPanel`, and `StatusBarFooter`, all of which inherit from the `UiPart` class.

The UI layouts are defined in corresponding `.fxml` files located in the `src/main/resources/view` folder.

Key responsibilities include:

- Executing user commands via the `Logic` component.
- Listening for data changes in the `Model` and updating the UI accordingly.
- Maintaining a reference to the `Logic` component for command execution.
- Depending on certain `Model` classes to display `Person` objects.

</details>

---

#### Logic Component

- **API**: [`Logic.java`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)
- The Logic component is responsible for interpreting and executing user commands.

<details>
<summary>Click to expand details</summary>

Here's a partial class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550" alt="Logic Component Class Diagram"/>

Key functionalities include:

- Interpreting user input and generating the corresponding `Command` object.
- Executing the command and generating a `CommandResult` object.
- Depending on the `Model` component to perform data operations.
- Managing various command parsers to handle specific command formats.

</details>

---

#### Model Component

- **API**: [`Model.java`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)
- The Model component manages the application's in-memory data.

<details>
<summary>Click to expand details</summary>

<puml src="diagrams/ModelClassDiagram.puml" width="450" alt="Model Component Class Diagram"/>

Key responsibilities include:

- Storing address book data and selected `Person` objects.
- Managing user preferences.
- Providing an unmodifiable view of lists of `Person` objects for UI binding.

</details>

---

#### Storage Component

- **API**: [`Storage.java`](https://github.com/AY2324S1-CS2103T-T12-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)
- The Storage component manages data persistence.

<details>
<summary>Click to expand details</summary>

<puml src="diagrams/StorageClassDiagram.puml" width="550" alt="Storage Component Class Diagram"/>

Key functionalities include:

- Saving and retrieving address book data and user preferences in JSON format.
- Implementing both `AddressBookStorage` and `UserPrefStorage` for flexibility.
- Depending on certain `Model` classes for data object definitions.

</details>

---

## Common Classes

Classes used by multiple components are housed in the `seedu.addressbook.commons` package.

---

## Patient Features

A `Person` object encapsulates various attributes:

- `Name`: Patient's first and last name (and middle name, if applicable)
- `Nric`: Patient's NRIC
- `Email`: Patient's email address
- `Phone`: Patient's phone number
- `Gender`: Patient's gender
- `Age`: Patient's age
- `BloodType`: Patient's blood type
- `Set<Allergy>`: Patient's allergies
- `UniqueRecordList`: Patient's records of past visits to the clinic.
- `UniqueAppointmentList`: Patient's scheduled appointments.
- `isPinned`: Patient's pin status.

Uniqueness of person is maintained through the `UniquePersonList`.

<puml src="diagrams/PersonClassDiagram.puml"/>

### Adding a Patient

#### Overview

The `addpatient` command integrates a new `Person` object with the patient's details in MedBook.

#### Related Classes and Methods

- `AddCommandParser#parse(String)`: Parses command input
- `AddCommand#execute(Model)`: Executes addrecord command
- `Model#addPerson(Person)`, `AddressBook#addPerson(Person)`, `UniquePersonList#add(Person)`: Adds a patient.

#### Implementation Steps

1. **Parse User Input**: `AddCommandParser` checks for necessary parameters and their validity.
2. **Create Record Object**: A `Person` object is instantiated during `AddCommandParser#parse(String)` and handed over to the `AddCommand`.
3. **Execute Command**: `AddRecordCommand#execute(Model)` adds the new `Person` to the `UniquePersonList` in the `AddressBook`.

<puml src="diagrams/AddPatientSequenceDiagram.puml" width="450" />

### Editing Patient Details

The `editpatient` mechanism is primarily handled by `EditCommand`.

### Workflow

1. **Initialization**: On startup, the `AddressBook` is populated with sample data.
2. **Execution**: The user modifies a patient’s details using the `editpatient` command, triggering updates in the `Model` and `AddressBook`.
3. **Update**: The patient’s details are updated and the new AddressBook is displayed.

**Related Classes and Methods:**

- `EditCommandParser`: Parses command input.
- `EditPersonDescriptor`: Holds editable patient details.
- `ModelManager#setPerson(Person,Person)`, `AddressBook#setPerson(Person,Person)`, `UniquePersonList#setPerson(Person,Person)`: Updates patient details.

**Sequence Diagram**: _Pending Implementation_

### Design Considerations

**Aspect: Edit Patient Execution:**

- **Alternative 1 (Current Choice)**: Create a copy of the `Person`, edit, then replace.
  - _Pros_: Future-proof, maintains data integrity.
  - _Cons_: Adds complexity, potential performance issues.
- **Alternative 2**: Directly edit the `Person` in the AddressBook.
  - _Pros_: Straightforward.
  - _Cons_: Limits future functionalities, potential data integrity issues.

### Searching a Patient

#### Overview

The `search` command filters `FilteredPersonList` of the list of patients using one or more keywords.

#### Related Classes and Methods

- `FindCommandParser#parse(String)`: Parses command input.
- `FindCommand#execute(Model)`: Executes searchrecord command.
- `Model#updateFilteredList(Predicate)`: Updates `FilteredPersonList` of the currently viewing patient.
- `NameContainsKeywordsPredicate#test(Record)`: Tests if `Patient` contains keyword(s).

#### Implementations Steps

1. **Parse User Input**: `FindCommandParser` checks for existence of the keyword(s) and creates an array of keywords.
2. **Create Predicate Object**: A `NameContainsKeywordsPredicate` object is instantiated during `FindCommandParser#parse(String)` and handed over to the `FindCommand`.
3. **Execute Command**: `Findommand#execute(Model)` finds patients containing keywords using `NameContainsKeywordsPredicate#test(Record)` and updates `FilteredPersonList`, which is the list of patients being viewed.

<puml src="diagrams/SearchSequenceDiagram.puml" width="450" />

### Pinning a Patient

#### Overview

The `pin` command pins a patient to the **Pinned Patient List**

#### Related Classes and Methods

- `PinCommandParser#parse(String)`: Parses command input
- `PinCommand#execute(Model)`: Executes `pin` command
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`: Updates the patient details
- `PinnedPatientList`: UI component which displays all patients with `isPinned` set to `true`

#### Implementations Steps

1. **Parse User Input**: `PinCommandParser` checks for the validity of the patient index
2. **Create Index Object**: An `Index` object is instantiated during `PinCommandParser#parse(String)` and passed over to the `PinCommand`
3. **Execute Command**: `PinCommand#execute(Model)` sets the `isPinned` status of the patient to `true`

<puml src="diagrams/PinSequenceDiagram.puml"/>

### Unpinning a Patient

#### Overview

The `unpin` command unpins a patient from the **Pinned Patient List**

#### Related Classes and Methods

- `UnpinCommandParser#parse(String)`: Parses command input
- `UnpinCommand#execute(Model)`: Executes `unpin` command
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`: Updates the patient details
- `PinnedPatientList`: UI component which displays all patients with `isPinned` set to `true`

#### Implementations Steps

1. **Parse User Input**: `UnpinCommandParser` checks for the validity of the pinned patient index
2. **Create Index Object**: An `Index` object is instantiated during `UnpinCommandParser#parse(String)` and passed over to the `UnpinCommand`
3. **Execute Command**: `UnpinCommand#execute(Model)` sets the `isPinned` status of the patient to `false`

<puml src="diagrams/UnpinSequenceDiagram.puml"/>

## Records Feature

### General Implementation Details

<puml src="diagrams/RecordClassDiagram.puml"/>

A `Record` object encapsulates various attributes:

- `DateTime`: Date and time of the patient's clinic visit
- `List<Condition>`: Patient's health conditions
- `List<Medication>`: Medications prescribed to the patient

Uniqueness of records is maintained through the `UniqueRecordList`.

### Adding a Record

#### Overview

The `addrecord` command integrates a new `Record` object with the patient's details in MedBook.

#### Related Classes and Methods

- `AddRecordCommandParser#parse(String)`: Parses command input
- `AddRecordCommand#execute(Model)`: Executes addrecord command
- `UniqueRecordList#add(Record)`: Adds a `Record` in the `UniqueRecordList`.
- `Model#updateRecordList(Person, Index)`, `AddressBook#setRecords(Person, Index)`, `UniqueRecordList#setRecords(UniqueRecordList)`: Updates record details with added record.
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`: Updates patient details.

#### Implementation Steps

1. **Parse User Input**: `AddRecordCommandParser` checks for necessary parameters and their validity.
2. **Create Record Object**: A `Record` object is instantiated during `AddRecordCommandParser#parse(String)` and handed over to the `AddRecordCommand`.
3. **Execute Command**: `AddRecordCommand#execute(Model)` adds the new `Record` to the patient's `UniqueRecordList`.

<puml src="diagrams/AddRecordSequenceDiagram.puml" width="450" />

### Deleting a Record

#### Overview

The `deleterecord` command deletes a specified record in `UniqueRecordList` of a patient.

#### Related Classes and Methods

- `DeleteRecordCommandParser#parse(String)`: Parses command input.
- `DeleteRecordCommand#execute(Model)`: Executes deleterecord command.
- `UniqueRecordList#remove(Record)`: Deletes a `Record` in the `UniqueRecordList`.
- `Model#updateRecordList(Person, Index)`, `AddressBook#setRecords(Person, Index)`, `UniqueRecordList#setRecords(UniqueRecordList)`: Updates record details with deleted record.
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`: Updates patient details.

##### Implementation Steps

1. **Parse User Input**: `DeleteRecordCommandParser` checks for the validity of the patient and record indices.
2. **Create Index Object**: Two `Index` objects, patient index and record index, are instantiated during `DeleteRecordCommandParser#parse(String)` and handed over to the `DeleteRecordCommand`.
3. **Execute Command**: `DeleteRecordCommand#execute(Model)` deletes specified record of the specified patient and updates `UniqueRecordList` of that patient.

<puml src="diagrams/DeleteRecordSequenceDiagram.puml" width="450" />

### Searching a Record

#### Overview

The `searchrecord` command filters `UniqueRecordList` of the currently viewing patient using one or more keywords.

#### Related Classes and Methods

- `FindRecordCommandParser#parse(String)`: Parses command input.
- `FindRecordCommand#execute(Model)`: Executes searchrecord command.
- `Model#updateFilteredRecordList(Predicate)`: Updates `UniqueRecordList` of the currently viewing patient.
- `RecordContainsKeywordsPredicate#test(Record)`: Tests if `Record` contains keyword(s).

#### Implementations Steps

1. **Parse User Input**: `FindRecordCommandParser` checks for existence of the keyword(s) and creates an array of keywords.
2. **Create Predicate Object**: A `RecordContainsKeywordsPredicate` object is instantiated during `FindRecordCommandParser#parse(String)` and handed over to the `FindRecordCommand`.
3. **Execute Command**: `FindRecordCommand#execute(Model)` finds records containing keywords using `RecordContainsKeywordsPredicate#test(Record)` and updates `UniqueRecordList` of the currently viewing patient.

<puml src="diagrams/FindRecordSequenceDiagram.puml" width="450" />

### Attaching Files to Patient Records

#### Overview

The "Attach Files to Patient Records" feature allows users to associate files with patient records, enhancing the completeness and accessibility of patient information.

#### Target Audience

This feature benefits healthcare professionals and medical staff who need to store and access additional patient-related documents, such as medical images, lab reports, or scanned documents.

#### Related Class and Methods

- `RecordCommand`: Manages the User Interface for each record.
- `Record`: Model class which stores patient records.
- `ModelManager#setPerson(Person,Person)`, `AddressBook#SetPerson(Person,Person)`, `UniquePersonList#setPerson(Person,Person)`: Updates patient details.

#### Implementation Steps

#### User Interface

##### Attaching Files

- Users can attach files to patient records through a user-friendly graphical interface.
- A dedicated button opens a file explorer, allowing users to select and attach files.
- The selected file's path is automatically stored in the `filePath` field of the associated `Record` instance.

##### Opening Attached Files

- To access attached files, users simply click on the file path displayed within the patient record.
- The application attempts to open the file using the default program associated with its file type.

#### Diagram

The following sequence diagram provides an overview of how the file attachment operation works:

<puml src="diagrams/AttachFileSequenceDiagram.puml" alt="AttachFileSequenceDiagram" />

#### Alternative Considerations

In designing this feature, we considered two primary approaches:

1. **Current Implementation (GUI)**:

   - _Pros_: The graphical user interface (GUI) for file attachments is user-friendly, especially for individuals less familiar with command-line interfaces (CLI).
   - _Cons_: It may be relatively slower for users proficient with CLI-based interactions.

2. **Command-Line Interface (CLI)**:
   - _Pros_: CLI-based file attachment would cater to users who prefer efficient, command-driven workflows.
   - _Cons_: May require a learning curve for users less experienced with command-line interactions.

Ultimately, the decision was made to implement the feature with a GUI to ensure accessibility and ease of use for a broader range of users, while still allowing for efficient management of patient records.

## Appointments Feature

### General Implementation Details

<puml src="diagrams/AppointmentClassDiagram.puml"/>

An `Appointment` is comprised of:

- `Name`: Appointment’s title.
- `DateTime`: Scheduling details.
- `Nric`: Nric of the patient involved.

Uniqueness is enforced through a `UniqueAppointmentList`.

#### Adding an Appointment

#### Overview

The `addappointment` command adds a new `Appointment` to MedBook.

### Implementation Steps

1. **Parse User Input**: `AddAppointmentCommandParser` checks for necessary parameters and their validity.
2. **Create Appointment Object**: An `Appointment` object is instantiated during `AddAppointmentCommandParser#parse(String)` and passed to the `AddAppointmentCommand`.
3. **Add Appointment**: `AddAppointmentCommand#execute(Model)` adds the new `Appointment` to the corresponding patient's `UniqueAppointmentList` and resets the `UniqueAppointmentList` of the `Model`.

<puml src="diagrams/AddAppointmentSequenceDiagram.puml"/>

#### Deleting an Appointment

##### Overview

The `deleteappointment` command deletes a specific `Appointment` from MedBook.

##### Related Classes and Methods

- `DeleteAppointmentCommandParser#parse(String)`: Parses command input
- `DeleteAppointmentCommand#execute(Model)`: Executes `deleterecord` command
- `UniqueAppointmentList#remove(Appointment)`: Deletes an `Appointment` from the `UniqueAppointmentList`
- `Model#setPerson(Person, Person)`, `AddressBook#setPerson(Person, Person)`, `UniquePersonList#setPerson(Person, Person)`: Updates patient details
- `Model#resetAppointmentList()`: Resets the `UniqueAppointmentList` of the `Model`

##### Implementation Steps

1. **Parse User Input**: `DeleteAppointmentCommandParser` checks for the validity of the `Appointment` index.
2. **Create Index Object**: An `Index` object is instantiated during `DeleteAppointmentCommandParser#parse(String)` and passed to the `DeleteAppointmentCommand`.
3. **Execute Command**: `DeleteAppointmentCommand#execute(Model)` deletes the specified `Appointment` from the corresponding patient's `UniqueAppointmentList` and resets the `UniqueAppointmentList` of the `Model`.

<puml src="diagrams/DeleteAppointmentSequenceDiagram.puml"/>

#### Viewing the Appointment Window

##### Overview

The `viewappointment` command opens/focuses the `AppointmentsWindow`.

##### Related Classes and Methods

- `ViewAppointmentCommand#execute(Model)`: Executes `viewappointment` command
- `MainWindow#handleAppointments()`: Opens/focuses the `AppointmentsWindow`
- `AppointmentsWindow`, `AppointmentListPanel`, `AppointmentCalenderPanel`: UI components which display the `Appointment` information
- `AppointmentsWindow#fillInnerParts`: Initializes `AppointmentListPanel` and `AppointmentCalendarPanel`, populating them with data from a `UniqueAppointmentList`

##### Implementation Steps

1. **Parse User Input**: `DeleteAppointmentCommandParser` checks for the validity of the `Appointment` index.
2. **Create Index Object**: An `Index` object is instantiated during `DeleteAppointmentCommandParser#parse(String)` and passed to the `DeleteAppointmentCommand`.
3. **Execute Command**: `DeleteAppointmentCommand#execute(Model)` deletes the specified `Appointment` from the corresponding patient's `UniqueAppointmentList` and resets the `UniqueAppointmentList` of the `Model`.

<puml src="diagrams/ViewAppointmentSequenceDiagram.puml"/>

### Design Considerations

**Aspect: Structure of the Appointment class:**

- **Alternative 1 (Current Choice)**: `Model` holds a `UniqueAppointmentList` consisting of all `Appointment` objects, each `Person` also has a `UniqueAppointmentList` consisting of all `Appointment` objects assigned to the person. Each `Appointment` object has the corresponding `Person` `NRIC` as a field.
  - _Pros_: Operations like searching and filtering for all appointments are easier when a centralized list is available.
  - _Cons_: Keeping the central `UniqueAppointmentList` in `Model` and individual lists in each `Person` synchronized can be challenging and might lead to data inconsistencies if not managed properly. Any change in an `Appointment` requires updates in two places, adding to the complexity and processing time.
- **Alternative 2**: Each `Person` holds their own `UniqueAppointmentList` consisting of all `Appointment` objects assigned to the person.
  - _Pros_: This approach simplifies the data model by avoiding the need for a centralized appointment list.
  - _Cons_: Operations that require knowledge of all appointments, like finding available slots or generating reports, become more complex, as they need to aggregate data from each Person.

## View Feature

### Implementation

The view mechanism is facilitated through `ViewCommand`, which takes a `PATIENTINDEX` as input and updates the `records` and `personBeingViewed` attributes in `AddressBook`.

Key components implemented include:

- `ViewCommandParser`: Parses command input to create a `ViewCommand` with the specified `PATIENTINDEX`.
- `AddressBook`: Contains methods such as `setRecords(Person)`, `getRecordList()`, and `getPersonBeingViewed()`.
- `RecordCard`: A UI component displaying a single record’s information.
- `RecordListPanel`: A UI component housing a list of `RecordCard`s.

These methods in `AddressBook` are exposed via the `Model` interface (`updateRecords(Person)`, `getRecordList()`, `getPersonBeingViewed()`), and their get variants are also available through the `Logic` interface.

### Usage Scenario

1. **Initialization**: Upon launch, `AddressBook` is populated with sample data. `MainWindow` invokes `getRecordList()` and `getPersonBeingViewed()` to initialize the `recordListPanel` and `personBeingViewedPanel`.

2. **View Command**: User executes `view 1`, initiating a `Model#updateRecords(Person)` call.

   > **Note**: A failed command will not trigger a `Model#updateRecords(Person)` call, preventing any updates to `records` and `personBeingViewed`.

3. **Update and Display**: `Model` updates `AddressBook`’s variables, displaying the patient's medical records and personal card in their respective UI panels.

The following sequence diagram shows how the view operation works:

<puml src="diagrams/ViewSequenceDiagram.puml" alt="ViewSequenceDiagram" />

### Design Considerations

#### How View Executes

- **Alternative 1**: (Chosen) Utilize `UniqueRecordList` for `records` and `UniquePersonList` for `personBeingViewed`.
  - _Pros_: Simpler implementation.
  - _Cons_: Potential memory usage issues.
- **Alternative 2**: Utilize `UniqueRecordList` for `records` and `Person` for `personBeingViewed`.
  - _Pros_: Reduced memory usage.
  - _Cons_: Increased complexity and required additional object manipulations.

---

### User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …      | I want to …                                                                                                | So that I can…                                                    |
| -------- | ----------- | ---------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------- |
| `* * *`  | user        | _add_ a patient’s medical records and contact details                                                      | keep track of them efficiently                                    |
| `* * *`  | user        | _view_ a list of all the patients in the app                                                               | quickly see all my patients at once                               |
| `* * *`  | user        | _delete_ a specific patient’s details from the ap                                                          | remove patients that are no longer relevant or needed             |
| `* * *`  | user        | _edit_ an existing patient’s details in the app                                                            | keep the information accurate and up-to-date                      |
| `* * *`  | user        | _search_ for specific patients using keywords such as patient’s name or medical record                     | easily search for and locate specific patients in the app         |
| `* * *`  | user        | see the app populated with sample data                                                                     | easily see how the app will look when it is in use                |
| `* * *`  | new user    | access a “help” page to view the app’s functionalities                                                     | learn how to use the application effectively                      |
| `* * *`  | new user    | _clear all_ current data                                                                                   | get rid of sample/experimental data I used for exploring the app  |
| `* * *`  | user        | exit the application and save the address book automatically                                               |                                                                   |
| `* *`    | busy user   | _pin_ a specific patient                                                                                   | remember to contact them                                          |
| `* *`    | user        | import my patient details into the app                                                                     | efficiently manage my existing patient information                |
| `* * `   | user        | reorganize the address book in terms of appointment date/time and/or alphabetical order of patients’ names | have a sorted list to for other purposes                          |
| `* *`    | user        | receive regular updates and bug fixes for the app                                                          | ensure that it remains functional and bug-free                    |
| `* *`    | user        | attach files such as lab reports and prescription images to a patient's profile                            | maintain a comprehensive record of all patient information        |
| `* *`    | user        | view a history log of all the changes made to a patient's record                                           | track updates and maintain a reliable record                      |
| `* *`    | expert user | separate my patients into different categories                                                             | easily filter out my patients accordingly                         |
| `* *`    | busy user   | clear the contacts related to a specific patient                                                           | remove them all at one go                                         |
| `*`      | user        | view a daily schedule of patient appointments within the app                                               | prepare for my daily patient consultations                        |
| `*`      | user        | reschedule or cancel appointments within the app                                                           | have flexibility in appointment dates                             |
| `*`      | user        | set access permissions(password protected)                                                                 | allow only authorized personnel to view or modify patient details |
| `*`      | user        | print a patient’s medical record directly from the app                                                     | facilitate physical record keeping and sharing of information     |
| `*`      | expert user | create reminders for my patients to follow up                                                              | make sure that patients remember their follow up appointment      |
| `*`      | expert user | record patients who didn’t show up                                                                         | to keep track of patients who tend to miss appointment dates      |
| `*`      | expert user | export patient data to a CSV file or other common formats                                                  | easily share or transfer data between different systems           |
| `*`      | busy user   | reminders for upcoming patient appointments                                                                | remember to attend all the consultations                          |
| `*`      | busy user   | blacklist certain patients                                                                                 | remove absurd patients                                            |

## Use Cases

### UC01 - Viewing Help

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  - User requests for help.
  - MedBook displays help information.
  - Use case ends.

### UC02 - Adding a Patient

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User requests to add a new patient.
  2. MedBook prompts the user to enter the patient's details.
  3. User enters the patient's details.
  4. MedBook validates the input and adds the patient to the list.
  5. MedBook confirms the addition to the user.
- **Extensions**:
  - 3a. MedBook detects an error in the entered patient's details.
    - 3a1. MedBook shows an error message and prompts the user to enter the details again.
    - Use case resumes at step 3.

### UC03 - Listing All Patients

- **Actors**: User (typically a healthcare professional)
- **Preconditions**: Patient list is displayed and has at least one patient entry.
- **Main Success Scenario (MSS)**:
  1. User requests to list patients.
  2. MedBook shows a list of patients.
- **Extensions**:
  - 2a. The list is empty.
    - 2a1. MedBook informs the user that the list is empty.
    - Use case ends.

### UC04 - Editing a Patient

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User lists all patients (UC03).
  2. User provides patient ID, detail field, and updated patient details.
  3. MedBook updates the patient entry.
  4. MedBook shows successful edit details.
- **Extensions**:
  - 2a. MedBook detects an error in the entered input.
    - 2a1. MedBook shows an error message.
    - Use case ends.

### UC05 - Locating a Specific Patient

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User requests to search for a patient.
  2. MedBook prompts the user to enter search criteria.
  3. User enters search criteria.
  4. MedBook performs a search and displays matching patients.
- **Extensions**:
  - 4a. No matches found.
    - 4a1. MedBook informs the user that there were no matches.
    - Use case ends.

### UC06 - Delete a Patient

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User lists all patients (UC03).
  2. User requests to delete a specific patient.
  3. MedBook prompts the user to enter the patient's ID.
  4. MedBook deletes the patient.
- **Extensions**:
  - 2a. MedBook detects an error in the entered ID.
    - 2a1. MedBook shows an error message.
    - Use case ends.

### UC07 - Pin a Patient

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one patient.
- **Main Success Scenario (MSS)**:
  1. User lists all patients (UC03).
  2. User requests to pin a specific patient.
  3. MedBook pins the patient.
- **Extensions**:
  - 2a. MedBook detects an error in the entered ID.
    - 2a1. MedBook shows an error message.
    - 2a2. MedBook requests for the correct ID.
    - 2a3. User enters new ID.
    - Steps 2a1-2a3 are repeated until the ID entered is correct.
    - Use case resumes from step 3.

### UC08 - Unpin a Patient

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one pinned patient.
- **Main Success Scenario (MSS)**:
  1. User requests to unpin a specific patient.
  2. MedBook unpins the patient.
- **Extensions**:
  - 1a. MedBook detects an error in the entered PINNEDID.
    - 1a1. MedBook shows an error message.
    - 1a2. MedBook requests for the correct PINNEDID.
    - 1a3. User enters new PINNEDID.
    - Steps 1a1-1a3 are repeated until the PINNEDID entered is correct.
    - Use Case resumes from step 2.

### UC09 - Searching for Patients

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User initiates a patient search using specific keywords.
  2. MedBook performs a case-insensitive search.
  3. MedBook returns a list of matching patients.
  4. User views the list.
- **Extensions**:
  - 3a. No matches found.
    - 3a1. MedBook displays a message: "No matches found."
    - Use case ends.

### UC10 - Adding an Appointment

- **Actor**: User
- **System**: MedBook
- **Main Success Scenario (MSS)**:
  1. User requests to add a new appointment.
  2. MedBook prompts the user to enter the appointment's details.
  3. User enters the appointment's details.
  4. MedBook adds the appointment to the list.
- **Extensions**:
  - 3a. MedBook detects an error in the entered appointement's details.
    - 3a1. MedBook shows an error message.
    - 3a2. MedBook requests for the correct appointment details.
    - 3a3. User enters new appointment details.
    - Steps 3a1-3a3 are repeated until the appointment details entered is correct.
    - Use Case resumes from step 4.

### UC11 - Deleting an Appointment

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one appointment.
- **Main Success Scenario (MSS)**:
  1. User requests to delete a new appointment.
  2. MedBook prompts the user to enter the appointment's ID.
  3. User enters the appointment's ID.
  4. MedBook deletes the appointment from the list.
- **Extensions**:
  - 3a. MedBook detects an error in the entered APPOINTMENTID.
    - 3a1. MedBook shows an error message.
    - 3a2. MedBook requests for the correct APPOINTMENTID.
    - 3a3. User enters new APPOINTMENTID.
    - Steps 3a1-3a3 are repeated until the APPOINTMENTID entered is correct.
    - Use Case resumes from step 4.

### UC12 - Viewing Appointments

- **Actor**: User
- **System**: MedBook
- **Preconditions**: There is at least one appointment.
- **Main Success Scenario (MSS)**:
  1. User requests to view appointments.
  2. MedBook shows the user all appointments

---

## Non-Functional Requirements

1. Should work on any mainstream OS with Java 11 or above.
   - Rationale: Ensures accessibility for users on different platforms.
   - Metric: Application functions correctly on Windows, macOS, and Linux.
2. Supports up to 1000 patients without performance issues.
   - Rationale: Ensures scalability for clinics and hospitals.
   - Metric: Application performs smoothly with a database of 1000 patients.
3. Faster operation with commands than mouse for proficient typists.
   - Rationale: Enhances productivity for users familiar with the command line.
   - Metric: Common tasks are completed faster using keyboard shortcuts than GUI.
4. Backward compatible with data from previous versions.
   - Rationale: Ensures smooth transition for existing users upgrading to a new version.
   - Metric: Users can open and interact with data files from previous versions without issues.
5. Usable by novices.
   - Rationale: Ensures the application is accessible to new users.
   - Metric: New users can perform basic tasks without referring to the user manual.
6. Ensures data integrity and security.
   - Rationale: Protects patient data from corruption and unauthorized access.
   - Metric: Application employs data validation, encryption, and access controls.
7. Provides comprehensive error messages and guidance for recovery.
   - Rationale: Helps users understand what went wrong and how to fix it.
   - Metric: Error messages include a description of the issue and steps for resolution.
8. Responsive design that adjusts to different screen sizes and resolutions.
   - Rationale: Ensures usability across various devices.
   - Metric: UI elements are usable and aesthetically pleasing on screens ranging from 13" laptops to 27" monitors.
9. Regular updates and maintenance.
   - Rationale: Ensures the application stays up-to-date with the latest features and security patches.
   - Metric: Application receives updates at least once every three months.
10. Comprehensive documentation and user guides available.
    - Rationale: Provides users with resources to understand and use the application effectively.
    - Metric: Documentation covers all features, includes screenshots, and is easy to navigate.

---

## Glossary

- **Mainstream OS**: Popular operating systems such as Windows, Linux, Unix, and OS-X.

- **Private Contact Detail**: Any contact-related information that is meant to remain confidential and not be disclosed to unauthorized individuals.

- **CLI (Command Line Interface)**: A user interface that allows users to interact with the software using text commands via a console or terminal.

- **GUI (Graphical User Interface)**: A user interface that allows users to interact with the software through graphical icons and visual indicators, as opposed to text-based interfaces.

- **Patient ID**: A unique identifier assigned to each patient for quick and error-free retrieval of their records.

- **Streamlined Workflow**: A smooth, efficient workflow designed to minimize unnecessary steps and optimize productivity.

- **Backward Compatibility**: The ability of the system to work with data and interfaces from earlier versions of the software.

- **Healthcare Professional**: An individual who provides healthcare services, such as doctors, nurses, and medical staff.

- **Typical Usage**: The standard or most common way that the software is utilized by the end-users.

- **Usability**: The ease with which users can learn to use the software and the efficiency they can achieve.

- **Performance Issues**: Any lag, delay, or inefficiency in the software’s response or processing time, especially noticeable when handling a large amount of data.

- **Search Algorithm**: The method used by the software to search for and retrieve patient information based on input keywords or parameters.

- **Error Message**: A notification displayed by the software to inform the user that an error has occurred, often accompanied by information on how to resolve the issue.

- **User**: An individual who interacts with the software, typically a healthcare professional in this context.

- **Patient Information**: Data related to a patient, including but not limited to their personal details, medical history, contact information, and any other relevant information.

- **Case-Insensitive Search**: A type of search that does not differentiate between uppercase and lowercase letters, ensuring that results are returned regardless of the case used in the search query.

- **Data Compatibility**: The ability of the software to properly read, interpret, and use data formatted or created in other versions or different configurations.

- **Novice User**: A user with limited experience and knowledge of the software or similar applications.

Adding to the glossary ensures that all potential users, regardless of their level of expertise, have a resource to refer to when they come across terms they are unfamiliar with. This helps in making the software more accessible and user-friendly.

---

## Documentation, Logging, Testing, Configuration, Dev-Ops

- [Documentation guide](Documentation.md)
- [Testing guide](Testing.md)
- [Logging guide](Logging.md)
- [Configuration guide](Configuration.md)
- [DevOps guide](DevOps.md)

---

## Appendix: Requirements

### Product Scope

**Target User Profile**:

- **Primary Users**: Doctors and healthcare professionals who handle a substantial number of patients on a regular basis.
- **Platform Preference**: Has a strong inclination towards using desktop applications, particularly those that support command line interfaces (CLI).
- **Technical Proficiency**: Is adept at utilizing CLI applications and prefers them for their speed and efficiency.

**Value Proposition**:

- **Efficient Patient Management**: Provides a streamlined and efficient solution for managing patient information, ensuring that healthcare professionals can access and modify patient data quickly.
- **Speed and Accessibility**: Designed to be significantly faster than conventional GUI applications, allowing users to execute commands and retrieve patient information in a matter of seconds.
- **Command Line Efficiency**: Leverages the power of CLI to offer advanced users the ability to perform tasks in a more direct and efficient manner, while still maintaining accessibility for those who prefer graphical interfaces.

## Appendix: Instructions for Manual Testing

### Launch and Shutdown

#### Initial Launch

1. Download and place the jar file in an empty folder.
2. Double-click the jar file.
   - Expected: GUI opens with sample contacts.

#### Saving Window Preferences

1. Resize and reposition the window, then close it.
2. Reopen the application.
   - Expected: Window retains its size and position.

---

### Deleting a Person

#### When All Persons are Shown

1. Prerequisites: Use `list` to show all persons.
2. Test Case: `delete 1`
   - Expected: First contact deleted, details shown in status message.
3. Test Case: `delete 0`
   - Expected: Error message displayed, status bar unchanged.
4. Other Test Cases: `delete`, `delete x` (where x > list size)
   - Expected: Error message displayed, status bar unchanged.

---

### Saving Data

#### Dealing with Missing/Corrupted Data Files

1. _[Provide instructions and expected behavior]_

---

### Adding a New Patient

#### Standard Procedure

1. Test Case: `add John Doe; Age: 30; Address: 123 Main St`
   - Expected: New patient "John Doe" is added to the list, details are shown in status message.
2. Test Case: `add; Age: 30; Address: 123 Main St`
   - Expected: Error message displayed, patient not added.
3. Test Case: `add John Doe; Age: thirty; Address: 123 Main St`
   - Expected: Error message displayed, patient not added.

---

### Editing a Patient's Details

#### When the Patient Exists

1. Prerequisites: Ensure the patient list is displayed and contains the entry you wish to edit.
2. Test Case: `edit 1; Age: 35`
   - Expected: Patient at index 1 has their age updated to 35. Details shown in status message.
3. Test Case: `edit x; Age: 35` (where x > list size)
   - Expected: Error message displayed, patient's details unchanged.

---

### Searching for Patients

#### When There Are Matching Entries

1. Prerequisites: Ensure the patient list contains entries that will match your search term.
2. Test Case: `search John`
   - Expected: List of patients with "John" in their name or details is displayed.

---

### Pinnning a Patient

#### Pinning a patient while all patients are being shown

1. Prerequisites: Ensure the patient list is displayed and contains the entry you wish to pin.
2. Test Case: `pin 1`
   - Expected: Patient at index 1 is pinned to the **Pinned Patient List**. Details shown in status message.
3. Test Case: `pin x` (where x > list size)
   - Expected: Error message displayed, **Pinned Patient List** unchanged.

---

### Unpinnning a Patient

#### Unpinning a patient

1. Prerequisites: Ensure the **Pinned Patient List** contains the entry you wish to unpin.
2. Test Case: `unpin 1`
   - Expected: Patient at index 1 of the **Pinned Patient List** is unpinned and no longer displayed in the **Pinned Patient List**. Details shown in status message.
3. Test Case: `unpin x` (where x > list size)
   - Expected: Error message displayed, **Pinned Patient List** unchanged.

---

### Adding an Appointment

#### Adding an appointment

1. Test Case: `addappointment`
   - Expected: New patient "John Doe" is added to the list, details are shown in status message.
2. Test Case: `add; Age: 30; Address: 123 Main St`
   - Expected: Error message displayed, patient not added.
3. Test Case: `add John Doe; Age: thirty; Address: 123 Main St`
   - Expected: Error message displayed, patient not added.

### Verifying Patient Data Integrity

#### After Operations

1. Prerequisites: Perform operations like adding, deleting, and editing patients.
2. Navigate to the folder where data is stored and open the data file.
   - Expected: All changes should be accurately reflected in the data file.

---

### Handling Invalid Commands

#### Input Mistakes

1. Test Case: `ad John Doe; Age: 30; Address: 123 Main St`
   - Expected: Error message displayed, suggesting the correct command format.
2. Test Case: `delet 1`
   - Expected: Error message displayed, suggesting the correct command format.

---

These additional sections aim to cover more aspects of the application, providing a thorough guideline for manual testing. Each section outlines the prerequisites, the test cases, and the expected outcomes to guide testers through the process.

## Appendix: Planned Enhancements

### Additional Navigation in Appointment Calender

#### Current Implementation

The current version of the Appointment Calendar allows users to view appointments in a calendar format. Navigation through the calendar is limited to sequential month traversal using 'Next' and 'Previous' buttons. This implementation, while functional, can be cumbersome for users who wish to view appointments that are several months away from the current date.

#### Proposed Enhancement

We plan to enhance the calendar navigation by introducing a more efficient way for users to view appointments. The enhancement will allow users to:

**Manual Selection of Month and Year**: A dropdown menu or a picker control will be integrated into the UI, enabling users to quickly jump to a specific month and year without sequentially navigating through each month.

**`viewcalender MM YYYY` command**: For users who prefer typing through the CLI, we will implement a command that allows them to view the calendar for a specific month and year. Users will be able to enter a command in the format viewcalendar MM YYYY (e.g., viewcalendar 12 2023 to view December 2023), and the calendar will update to display the selected month and year.

### Enhanced Appointment Calender UI

#### Current Implementation

The existing UI of the Appointment Calendar displays a truncated version of the appointment name and limits the visible appointments to only two per day. This restriction can lead to incomplete information visibility which is not optimal for efficiency and user experience.

#### Proposed Enhancement

To improve the user interface and overall user experience of the Appointment Calendar, the following enhancements are proposed:

**Expanded Appointment Visibility**: Modify the calendar layout to allow for the display of more than two appointments per day. This could involve redesigning the day cells to accommodate more entries or implementing a scrolling mechanism within each day cell for days with numerous appointments.
Ensure that the UI remains uncluttered and user-friendly, even with the inclusion of more appointment entries per day.

**Full Appointment Name Display**: Adjust the UI to display the full name of each appointment, rather than a truncated version. This will enable users to quickly identify appointments at a glance without needing to hover over or click into the appointment for full details.
Implement a dynamic text resizing or wrapping feature within each calendar entry to ensure that longer appointment names fit within the allocated space without compromising readability.

**Responsive and Adaptive Design**: Enhance the calendar’s responsive design so it adapts effectively to different screen sizes and resolutions. This ensures that the increased information density does not negatively impact users on smaller screens or mobile devices.

### Enhanced NRIC Parameter for Patients

#### Current Implementation

In the current implementation the NRIC parameter for patient identification is limited in its format. It accepts an entry consisting of an alphabet, followed by seven digits, and then another alphabet. This format, while broadly useful, does not align completely with real-world scenarios, particularly in Singapore, and lacks the flexibility required for foreign patients.

#### Proposed Enhancement

To make the NRIC parameter more inclusive and reflective of real-world use cases, especially in Singapore, we propose enhancing the NRIC parameter with the following features:

**Restricted First Alphabet**: The first alphabet in the NRIC will now be restricted to specific letters, such as 'S' and 'T', which are currently used in Singapore. This change aligns the system more closely with the actual format of NRICs in Singapore.

**Optional Passport Number Support**: To accommodate foreign patients who do not have an NRIC, the system will be enhanced to accept passport numbers as an alternative identifier. This feature is particularly important for private clinics that cater to a diverse patient base, including non-residents and tourists.

#### Accepting / in Name parameter

Due to current constraints in the Parser which causes / to be parsed as tags, the "/" character cannot be entered into the name parameter. As such, users would currently not be able to enter "Muhammed Ali s/o Muhammed Ali". We would implement this feature in the future for even more accurate patient naming.
