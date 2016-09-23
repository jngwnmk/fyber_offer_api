# fyber_offer_api

Android Developer Challenge
The whole idea of this challenge is to use the Fyber Mobile Offers API (JSON) and render the results of the response in a native Android application.
Action Steps

1. Create a form asking for the variable params (uid, API Key, appid, pub0)
2. Make the request to the API passing the params and the authentication hash
3. Get the result from the response.
4. Check the returned hash to check that it’s a real response (check signature)
5. Render the offers in a view.
    A. If we have offers there we render them (title, teaser, thumbnail hires and payout)
    B. If we have no offers there we render a message like ‘No offers’
6. Create functional and unit tests (choose your tool)
7. Check Fyber Mobile Offer API Documentation at http://developer.fyber.com/ content/android/offer-wall/offer-api/
8. Create a github repository and send us the URL with the solution of the
challenge

Params to be used
format: json
appid: Application ID, provided as simple data   uid: User ID, provided as simple data   device_id: use Android advertising identifier   locale: provided as simple data
ip: provided as simple data
offer_types: 112

Sample app data:
appid: 2070
uid: spiderman
locale: ‘DE’
ip: ‘109.235.143.113’
API Key: 1c915e3b5d42d05136185030892fbb846c278927