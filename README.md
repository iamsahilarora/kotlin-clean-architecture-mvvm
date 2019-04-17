# kotlin-clean-architecture-mvvm
Kotlin Sample of Clean Architecture using
MVVM ,Retorfit2, Koin, Coroutines, AndroidX, Android Jetpack, DataBinding 


MVVM (Model View ViewModel Reperesentation) Flow of sample app 

View ->  Request data from viewModel
     ->  Observe viewmodel LiveData for response  

ViewModel  -> Having all LiveData of DataModels   
           -> Call getDataReqeust from Repository 
           -> Send requested param and live data to Repository  

Repository  -> Get RequestData & LiveData as Param from ViewModel 
            -> Decide to fetch data from DB/Network 
            -> Fetch data and post it on LiveData get from viewmodel
            
            
DI (Koin Library is used for Define DI-Dependencies Injections ) 

Retirfit2 (Used to call Data Fetch  from network)
Coroutines(Used to Define a thread or scope to call API's )
RoomDB (Used to store/Fetch data from Database)



