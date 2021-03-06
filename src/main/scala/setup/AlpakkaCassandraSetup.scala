package setup

import akka.stream.alpakka.cassandra.CassandraSessionSettings
import akka.stream.alpakka.cassandra.scaladsl.{CassandraSession, CassandraSessionRegistry}
import com.datastax.oss.driver.api.core.cql.{BoundStatement, PreparedStatement}
import config.AppConfig._

trait AlpakkaCassandraSetup {

    lazy val sessionSettings: CassandraSessionSettings = CassandraSessionSettings()

    implicit lazy val cassandraSession: CassandraSession =
      CassandraSessionRegistry.get(system).sessionFor(sessionSettings)

    val insertStatementBinder: (model.Video, PreparedStatement) => BoundStatement =
      (video, preparedStatement) => preparedStatement.bind(video.userId, video.videoId, video.title, video.creationDate)
}
