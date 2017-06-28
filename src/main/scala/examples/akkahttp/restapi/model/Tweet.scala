package examples.akkahttp.restapi.model

import spray.json.{DefaultJsonProtocol, RootJsonFormat}


case class Tweet(author: String, body: String)


object TweetProtocol extends DefaultJsonProtocol {

  implicit val TweetFormat: RootJsonFormat[Tweet] = jsonFormat2(Tweet.apply)
}
