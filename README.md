# kotlin-clean-architecture-mvvm
This Repository contains a Detailed Sample app that Implements MVVM clean Architecture in Kotlin using
Retorfit2, Koin, Coroutines, AndroidX, Android Jetpack, DataBinding 

#### The app has following packages:

 - **config:** App Configuration Constants Files 
 - **di:** Dependencies Provider Classes using **Koin**
 - **model:** its include all **DATA Classes** like beans,Repository classes,network API Call classes ,Database classes
 - **utils:** Utilities Classes 
 - **view:** View Classes Fragments/Activities 
 - **view_model:** ViewModels Classes that provide data to View 

 
 
 
## MVVM (Model View ViewModel Reperesentation) Flow of sample app 

- **View** 
  - >  Request data from viewModel
  - >  Observe viewmodel LiveData for response  


- **ViewModel**  
  - > Having all LiveData of DataModels   
  - > Call getDataReqeust from Repository 
  - > Send requested param and live data to Repository  

- **Repository** 
  - > Get RequestData & LiveData as Param from ViewModel 
  - > Decide to fetch data from DB/Network 
  - > Fetch data and post it on LiveData get from viewmodel
            
  
#### Libraries Used   
- **Koin:**  Library is used for Define DI-Dependencies Injections

- **Retrofit2:** Used to call Data Fetch  from network

- **Kotlin Coroutines:** Used to Define a thread or scope to call API's 

- **RoomDB** Used to store/Fetch data from Database



