# Bajalang App
Location Based app with architecture component `MVVM` (Model View ViewModel) that use a-rest as a service
Google Maps JSON for 
[Kota Kotamobagu](http://maps.googleapis.com/maps/api/geocode/json?latlng=0.7368422,124.3155934&sensor=true), 
[Manado](http://maps.googleapis.com/maps/api/geocode/json?latlng=1.5408144,124.7144464&sensor=true). 
FYI: Current data in database are available just in this city - Kotamobagu and Manado 

## Library

  - [Architecture Component](https://developer.android.com/topic/libraries/architecture/adding-components.html)
  - [Dagger 2](https://github.com/google/dagger)
  - [RxJava](https://github.com/ReactiveX/Rxjava)
  - [RxAndroid](https://github.com/ReactiveX/RxAndroid)
  - [Retrofit](https://github.com/square/retrofit)
  - [OkHttp](https://github.com/square/okhttp)
  - [Anko](https://github.com/Kotlin/anko)
  - [Gif Drawable](https://github.com/koral--/android-gif-drawable)

### JSON Data

    
     List :  [
                {
                    "id": 11,
                    "location_id": 2,
                    "category_id": 1,
                    "name": "wisata bahari seafood restaurant",
                    "lat": 1.464274,
                    "lon": 124.826148,
                   
                },
                {
                    "id": 12,
                    "location_id": 2,
                    "category_id": 1,
                    "name": "rumah makan raja oci",
                    "lat": 1.4887921,
                    "lon": 124.847259,
               
                }
             ]
    
    Detail : {
                "status": 200,
                "error": false,
                "message": "success",
                "detail": {
                    "name": "universitas sam ratulangi",
                    "lat": 1.4564212,
                    "lon": 124.8282569
                }
             }

    
## TODO List
  - [ ] Clean up code style.

## REST Service
- a-rest : https://github.com/aasumitro/a-rest

