---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# User Guide

Medbook is a **desktop application** crafted specifically for **doctors** and **medical administrative assistants** to manage patient details and medical records.

Our address book offers a streamlined interface for tracking patient details and medical records, ensuring that vital information is organized, accessible, and secure at all times.

<!-- * Table of Contents -->
<page-nav-print />

---

## How to Navigate the User Guide

This user guide serves to provide clear instructions and insights, empowering users to effectively utilize all features and functionalities with confidence and ease.

The table of contents can be used at the side to seamlessly jump between sections.

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
   - [How do I check my version of Java?](#faq)
1. Download the latest jar file (`medbook.jar`) from [here](https://github.com/AY2324S1-CS2103T-T12-4/tp/releases).

1. Move the file to the folder you want to use as the _home folder_ for your MedBook.

1. Open a command terminal
   - For Windows Users:
     - Press the Windows icon and search for and open up`Powershell`
   - For Mac Users:
     - Open a new Terminal window (Command + Space > type Terminal > Enter).

Navigate to the folder you put the jar file in using `cd` and use the `java -jar medbook.jar` command to run the application.

The application should open up similar to the image below.
![Ui](images/MedBookUi.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all patients.

   - `addpatient n/John Doe e/johndoe@gmail.com p/12345678 g/M a/26 bt/AB+ al/Penicillin` : Adds a new patient named John Doe with basic information about the patient

   - `delete 3` : Deletes the 3rd patient shown in the current list.

   - `clear` : Deletes all patient.

   - `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

---

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [al/ALLERGIES]` can be used as `n/John Doe al/Pencillin` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[al/ALLERGIES]…​` can be used as ` ` (i.e. 0 times), `al/Pencillin`, `al/Pollen al/Dust` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- `PATIENTID` refers to the unique patient ID shown in the displayed patient list. The ID **must be a positive integer** 1, 2, 3, …​

### Viewing help : `help`

Shows a message explaning how to access the help page.

Format: `help`

### Adding a patient: `addpatient`

Adds a patient's contact and medical details.

Format: `addpatient n/NAME e/EMAIL p/PHONE g/GENDER a/AGE bt/BLOODTYPE [al/ALLERGIES]...`

- Adds a patient with information including: `NAME`, `EMAIL`, `GENDER`, `PHONE`, `AGE`, `BLOODTYPE`, `ALLERGIES`

Constraints

- `NAME`: Up to 256 alphanumeric characters
- `EMAIL`: Valid email format: `email@domain.com`
- `GENDER`: Either `M` or `F`
- `PHONE`: Must be 8 digits
- `AGE`: Must be a positive integer
- `BLOODTYPE`: Must be one of `A-`, `A+`, `B-`, `B+`, `AB-`, `AB+`, `O-`, `O+`
- `ALLERGIES`: Up to 256 alphanumeric characters

Examples:

`addpatient n/John Doe e/johndoe@gmail.com p/12345678 g/M a/26 bt/AB+ al/Penicillin`
Adds a patient named John Doe who is a Male, 26 years old, has AB+ Blood Type, and is allergic to Penicillin.
His email and phone number is johndoe@gmail.com and 12345678, respectively.

### Adding Medical Records : `addrecord`

Adds a patient's medical record.

Format: `addrecord PATIENTID d/DATETIME c/CONDITIONS... [m/MEDICATION]...`

- Adds a medical record to the patient with the corresponding `PATIENTID`

Constraints

- `DATETIME`: Valid Date and Time format: `DD-MM-YYYY HHMM`
- `CONDITION`: Up to 256 alphanumeric characters
- `MEDICATION`: Up to 256 alphanumeric characters

Examples:

- `addrecord 3 d/18-09-2023 1800 c/Fever m/Paracetamol`
  Adds a record to the patient with the `PATIENTID` of `3`.
  The record says that the patient visited the clinic on September 18th, 2023 at 6PM with a Fever and was prescribed Paracetamol.

### Listing all persons : `list`

Shows a list of all patients in the Medbook.

Format: `list`

- All patients in the Medbook will always be visible on the screen in the **MIDDLE** column.

Examples:

- `list` lists all the patients in the `MIDDLE` column.

### View patient records : `view`

Displays the medical records of the specified patient in the **RECORDS** list

Format: `view PATIENTID`

- The medical records of the patient with the corresponding `PATIENTID` will be displayed on screen in the **RECORD** list.
- Information about the patient will be displayed in the **PATIENT BEING VIEWED** section.

Examples:

- `view 2` displays the medical records of the patient with the `PATIENTID` of `2`. The displayed records will be in the `RECORDS` list and patient information will be in the `PATIENT BEING VIEWED` section.

### Editing detail of a patient : `editpatient`

Edits an existing patient in MedBook.

Format: `editpatient PATIENTID/FIELD/NEWVALUE`

- Edits the patient at the specified `PATIENTID`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
- The `FIELD` must be provided.
- Accepted `FIELD` : **name**, **email**, **phone**, **gender**, **age**, **blood**, **allergy**.
- Existing values in the `FIELD` will be updated to the `NEWVALUE` input.
- Constraints are as state in `addpatient` command.

Examples:

- `editdetail 1/email/johndoe_updated@gmail.com` Edits the email address of the 1st person to be `johndoe_updated@gmail.com`.
- `editdetail 2/phone/92345678` Edits the name of the 2nd person to be `92345678`.
- `editdetail 3/allergy/aspirin` Edits the allergy of the 3rd person to be `aspirin`.

### Editing a record of a patient : `editrecord`

Edits a record of an existing patient in MedBook.

Format: `editrecord PATIENTID/RECORDID FIELD/NEWVALUE`

- Edits a record at the specific `RECORDID` of the patient at the specified `PATIENTID`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …
- The `FIELD` must be provided.
- Accepted `FIELD` : **d** (for dateTime), **c** (for conditions).
- Existing values in the `FIELD` will be updated to the `NEWVALUE` input.
- Multiple editing of condition fields are allowed to represent a new set of conditions.
- Contraints are as stated in `addrecord` command.

Examples:

- `editrecord 1/1 d/25-10-2023 1200` Edits the dateTime of the 1st record of the 1st patient to be `25-10-2023 1200`.
- `editrecord 1/1 c/Fever` Edits the conditions of the 1st record of the 1st patient to be `Fever`.
- `editrecord 1/1 c/Headache c/Flu` Edits the conditions of the 1st  of the 1st patient to be `Headache, Flu`.

### Locating persons by keywords: `search`

Searches the patient with the corresponding `KEYWORD`

Format: `search KEYWORD...`

- The search is case-insensitive. e.g `Penicillin` will match `penicillin`
- A patient's name and details will be searched.
- Patients matching at least one keyword will be returned.
  e.g. `arm fracture` will return patients with `burnt arm` and `leg fracture`

Examples:

- `search Penicillin` returns a list of patients with `Penicillin` in their details.

### Deleting a patient : `delete`

Deletes the specified patient.

Format: `delete PATIENTID`

- Deletes the patient at the specified `PATIENTID`.

Examples:

- `delete 2` deletes the patient with the `PATIENTID` of `2` from the app.

### Pinning a Patient : `pin`

Pins the specified patient to the **Pinned Patient** list.

Format: `pin PATIENTID`

- The pinned patient will always be visible on screen in the **Pinned Patient** list.

Examples:

- `pin 2` pins the patient with the `PATIENTID` of `2` to the **Pinned Patient** list.

### Unpinning a Patient : `unpin`

Unpins the specified patient from the **Pinned Patient** list.

Format: `unpin PATIENTID`

- The `PATIENTID` for the unpin command corresponds to the ID as it appears in the **Pinned Patient** list.

Examples:

- `unpin 2` unpins the patient with the `PATIENTID` of `2` as per the **Pinned Patient** list.

### Adding an Appointment : `addappointment`

Adds an appointment.

Format: `addappointment PATIENTID n/APPOINTMENTNAME d/DATETIME`

- Adds an appointment with patient with the corresponding `PATIENTID` to the **Appointments** list.

Constraints:

- `DATETIME`: Must adhere to `DD-MM-YYYY HHMM` format
- `APPOINTMENTNAME`: Up to 256 alphanumeric characters

Examples:

- `addappointment 2 n/Eye Examination d/10-10-2023 1800` adds an Eye Examination for the patient with the `PATIENTID` of `2`. The appointment is set for 10th October 2023 at 6:00pm.

### Clearing all entries : `clear`

Clears all entries from the medbook.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

MedBook data is securely encrypted and saved to the hard disk automatically after any command that changes the data. This ensures the privacy and safety of your data. When MedBook is restarted, the data is decrypted and loaded back into the application.

### Editing the data file

MedBook data are saved automatically as a JSON file [JAR file location]/data/medbook.json. As the data is encrypted, users will not be able edit this files to directly make changes to the data files. This also prevents unauthorised access of the system.

<box type="warning" seamless>
Caution:
Do not make changes to the data file.
</box>

## FAQ

**Q**: How do I check my java version?<br>
**A**: Open a command prompt and type `java -version`. If you do not have Java installed, you can download it [here](https://www.oracle.com/java/technologies/downloads/#java11).

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and copy the data folder over to the same location in the other computer.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

## Command summary


| Action              | Format, Examples                                                                                                                       |
| ------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| **Add Patient**     | `addpatient n/NAME e/EMAIL p/PHONE …​` <br> e.g., `addpatient n/John Doe e/johndoe@gmail.com p/12345678 g/M a/26 bt/AB+ al/Penicillin` |
| **Add Record**      | `addrecord PATIENTID c/CONDITION... d/DATETIME [m/MEDICATION]...`<br> e.g., `addrecord 2 c/Fever d/10-10-2020 1900 m/Painkiller`       |
| **Add Appointment** | `addappointment PATIENTID n/APPOINTMENTNAME d/DATETIME`<br> e.g., `addappointment 2 n/Eye Exam d/10-10-2020 1900`                      |
| **List**            | `list`                                                                                                                                 |
| **View**            | `view PATIENTID`<br> e.g., `view 2`                                                                                                    |
| **Edit Patient** | `editpatient PATIENTID/FIELD/NEWVALUE`<br> e.g.,`editpatient 1/email/johndoe_updated@gmail.com`                                        |
| **Edit Record**  | `editrecord PATIENTID/RECORDID FIELD/NEWVALUE`<br> e.g.,`editrecord 1/1 d/25-10-2023 1200`                 |
| **Search**          | `search [KEYWORDS]`<br> e.g., `find James Jake`                                                                                        |
| **Delete**          | `delete PATIENTID`<br> e.g., `delete 3`                                                                                                |
| **Pin**             | `pin PATIENTID`<br> e.g., `pin 2`                                                                                                      |
| **Unpin**           | `unpin PATIENTID`<br> e.g. `unpin 2`                                                                                                   |
| **Clear**           | `clear`                                                                                                                                |
| **Exit**            | `exit`                                                                                                                                 |

