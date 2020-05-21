Dynamic Swing Designer

*Update for the optional part:
Used JavaBeans serialization in order to store all the components from the DesignPanel in XML
+ loading componens from such an xml based on the same logics
Added specific buttons


I have mostly just respected the specs, which means:
Created the MainFrame which contains a DesignFrame and a ControlPanel.
Both DF and CP have a reference to the mainFrame in order to pass user's input from one another. Depending on that input, 
the CP will load the specific class and call the addComponent() function from the DP which will actually draw it accordingly.
Note: I have also added a screenshot from runtime