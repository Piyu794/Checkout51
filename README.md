# Checkout51 &mdash; Sample App

This is a sample app provided as an assignment that uses Android Architecture Components.

## About App

The App will display list of items & available cashback with them. User can sort the list by Name & Cashback Value as well.

# Introduction
### Functionality:
The app is composed of 3 packages.
  * View
  * Model
  * ViewModel
 
#### View:
It contains ```MainActivity``` & ```Adapter```

List are getting observed from ```ViewModel``` & displaying in ```Adapter```

#### Model:
It contains ```Network```, ```Responses``` & ```Repository```

Network Responses are stored in ```Responses``` Package.

Netowrk Client & Api are initialized in ```Network``` Package.

```Repository``` is fetching the list of items over Network.

#### ViewModel:
It contains ```ItemViewModel```

```ItemViewModel``` is observing the list from ```Repository``` & exposing them so that ```MainActivity``` can observe them.

Sorting Functionality is also happening in ```ItemViewModel```

## Libraries:
1. AndroidX Library
2. Android Architecture Components
3. Retrofit for REST api communication
4. Picasso for image loading
5. Butterknife for View Injection
