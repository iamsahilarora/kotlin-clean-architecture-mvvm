# Kotlin Clean Architecture (MVVM)

## Overview
This Repository contains a Detailed Sample app that Implements MVVM clean Architecture in Kotlin using
Retorfit2, Koin, Coroutines, AndroidX, Android Jetpack, DataBinding 

### The app has following packages

 - **config** its include App Configuration Constants Files 
 
 - **di** its include Dependencies Injections Modules using **Koin**
 
 - **model** its include given sub modules 
 
   - **local** its include Room DB Dao,Entities and Shared Preferences Classes  
   
   - **remote** its include Network Call classes like APIConstants,ApiServices using **Retrofit**
   
   - **repo** its include all repository classes which handle the data requests coming from viewmodels 
   
   - **beans** its include all data classes,models and room db entities used in application
   
 - **utils** Utilities Classes 
 
 - **view** View Classes Fragments/Activities/Adapters 
 
 - **view_model** ViewModels Classes that provide data to View 

 
 
 
## MVVM (Model View ViewModel Reperesentation) Flow of sample app 

- **View** 
  - >Request data from viewModel
  
  - >Observe viewmodel LiveData for response  


- **ViewModel**  
  - >Having all LiveData of DataModels   
  
  - >Call getDataReqeust from Repository
  
  - > Send requested param and live data to Repository  

- **Repository** 
  - > Get RequestData & LiveData as Param from ViewModel 
  
  - > Decide to fetch data from DB/Network 
  
  - > Fetch data and post it on LiveData get from viewmodel
            
  
### Libraries Used   
- **Koin**  Library is used for Define DI-Dependencies Injections

- **Retrofit2** Used to call Data Fetch  from network

- **Kotlin Coroutines** Used to Define a thread or scope to call API's 

- **RoomDB** Used to store/Fetch data from Database



