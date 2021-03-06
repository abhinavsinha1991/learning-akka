package examples.akkahttp.restapi.db

import examples.akkahttp.restapi.model.TweetEntity
import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.bson.{BSONDocument, BSONObjectID}

import scala.concurrent.ExecutionContext


object TweetManager {
  import MongoDB._

  val collection = db[BSONCollection]("tweets")

  def save(tweetEntity: TweetEntity)(implicit ec: ExecutionContext) =
    collection.insert(tweetEntity).map(_ => Created(tweetEntity.id.stringify))

  def findById(id: String)(implicit ec: ExecutionContext) =
    collection.find(queryById(id)).one[TweetEntity]

  def deleteById(id: String)(implicit ec: ExecutionContext) =
    collection.remove(queryById(id)).map(_ => Deleted)

  def find(implicit ec: ExecutionContext) =
    collection.find(emptyQuery).cursor[BSONDocument].collect[List]()

  private def queryById(id: String) = BSONDocument("_id" -> BSONObjectID(id))

  private def emptyQuery = BSONDocument()
}
