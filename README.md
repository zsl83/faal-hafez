# faal-hafez

This Java code is designed to create a graphical user interface (GUI) application for fetching and displaying poems by the Iranian poet Hafez along with their interpretations from an online API.

1. **Main Class**: This class lays the groundwork for the program and calls the main GUI.

2. **FaalGUI Class**: It's in charge of designing and managing the user interface. This class has two pages: one for the main screen and another for displaying the poems and their interpretations. It uses Swing classes for designing the user interface and the HttpClient class to send requests to the API.

3. **Faal Class**: This class defines the data structure for a poem. It has two fields: one for the poem text and the other for its interpretation. The Jackson library is used to convert JSON data from the API into Java objects.

To establish communication with the API and fetch data, the HttpClient class is used. This class contains a method called get that sends a GET request to the desired API address and receives its response. After receiving the response from the API, JSON data is converted into Java objects using the Jackson library. This library provides the ability to deserialize JSON data, so the received API data is converted into Java objects and displayed in the user interface.

For example, in the FaalGUI class, the getFaal method is used to send a request to the API and fetch data, while the FaalPage method is used to display the received information in the user interface.

Overall, this program fetches poems by Hafez and their interpretations from an API and presents them in a clear graphical user interface.

here ypu can see some pictures and examples of program.


<img width="509" alt="faal1" src="https://github.com/zsl83/faal-hafez/assets/153368672/17d6b2c1-0fbb-4042-957b-5147579c1649">


<img width="507" alt="faal2" src="https://github.com/zsl83/faal-hafez/assets/153368672/0a19b55c-3164-4c8a-ac08-857ddc8fc91e">


