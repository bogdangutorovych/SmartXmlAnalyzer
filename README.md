# Smart XML Analyzer

Program that analyzes HTML and finds a specific element, even after changes, using a set of extracted attributes

## Running

```
<platform> <program_path> <input_origin_file_path> <input_other_sample_file_path> <origin_element_id>
```
First 2 parameters are mandatory.

Third parameter is optional.
 
If it absents, program will search for default element id - "make-everything-ok-button"

```
 java -jar SmartXmlAnalyzer.jar sample-0-origin.html sample-2-container-and-clone.html
``` 

### Output example:

```

Original element attributes:
+--------------------------------+--------------------------------------------------------------+
| Attribute name                 | Attribute value                                              |
+--------------------------------+--------------------------------------------------------------+
| id                             | make-everything-ok-button                                    |
| class                          | btn btn-success                                              |
| href                           | #ok                                                          |
| title                          | Make-Button                                                  |
| rel                            | next                                                         |
| onclick                        | javascript:window.okDone(); return false;                    |
| text                           | Make everything OK                                           |
+--------------------------------+--------------------------------------------------------------+
 
Found 1 matched element(s):
Path: html / body[1] / div / div[1] / div[2] / div / div / div[1] / div / a
+--------------------------------+--------------------------------------------------------------+
| Attribute name                 | Attribute value                                              |
+--------------------------------+--------------------------------------------------------------+
| rel                            | next                                                         |
| href                           | #ok                                                          |
| text                           | Make everything OK                                           |
| title                          | Make-Button                                                  |
+--------------------------------+--------------------------------------------------------------+

```