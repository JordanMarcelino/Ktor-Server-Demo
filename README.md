# Ktor-Server-Demo

# URL : https://ktor-server-demo.herokuapp.com

# API Endpoints

# GET : /boruto/ninjas

Headers:

- Content-Type: application/json
- Cache-Control: public, max-age=35.536.000, immutable <br>

Query Parameters:

- page : Integer (default: 1) (optional) (1-5)
- limit : Integer (default: 3) (optional)

Response:

- Status : 200
- Body :

```` json
    {
        "success":true,
        "message":"OK",
        "prevPage":null,
        "nextPage":2,
        "ninjas":[
                   {
                       "id":1,
                       "name":"Sasuke",
                       "image":"/images/sasuke.jpg",
                       "about":"Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
                       "rating":5.0,
                       "power":98,
                       "month":"July",
                       "day":"23rd",
                       "family":["Fugaku","Mikoto","Itachi","Sarada","Sakura"],
                       "abilities":["Sharingan","Rinnegan","Sussano","Amateratsu","Intelligence"],
                       "natureTypes":["Lightning","Fire","Wind","Earth","Water"]
                    },
                    {
                       "id":2,
                       "name":"Naruto",
                       "image":"/images/naruto.jpg",
                       "about":"Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage.",
                       "rating":5.0,
                       "power":98,
                       "month":"Oct",
                       "day":"10th",
                       "family":["Minato","Kushina","Boruto","Himawari","Hinata"],
                       "abilities":["Rasengan","Rasen-Shuriken","Shadow Clone","Senin Mode"],
                       "natureTypes":["Wind","Earth","Lava","Fire"]
                    },
                    {
                       "id":3,
                       "name":"Sakura",
                       "image":"/images/sakura.jpg",
                       "about":"Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
                       "rating":4.5,
                       "power":92,
                       "month":"Mar",
                       "day":"28th",
                       "family":["Kizashi","Mebuki","Sarada","Sasuke"],
                       "abilities":["Chakra Control","Medical Ninjutsu","Strength","Intelligence"],
                       "natureTypes":["Earth","Water","Fire"]
                   },
                  ],
        "lastUpdated":1650692420648
    }
````

# GET : /search/ninjas
Headers:

- Content-Type: application/json
- Cache-Control: public, max-age=35.536.000, immutable <br>

Query Parameters:

- query : String 

Response:

- Status : 200
- Body :
```` json
    {
        "success":true,
        "message":"OK",
        "prevPage":null,
        "nextPage":null,
        "ninjas":[
                    {
                        "id":1,
                        "name":"Sasuke",
                        "image":"/static/sasuke.jpg",
                        "about":"Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
                        "rating":5.0,
                        "power":98,
                        "month":"July",
                        "day":"23rd",
                        "family":["Fugaku","Mikoto","Itachi","Sarada","Sakura"],
                        "abilities":["Sharingan","Rinnegan","Sussano","Amateratsu","Intelligence"],
                        "natureTypes":["Lightning","Fire","Wind","Earth","Water"]
                        }
                  ],
        "lastUpdated":null
    }
````
