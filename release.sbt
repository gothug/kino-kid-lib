import sbtrelease._
import ReleaseStateTransformations._
import sbt.Keys._
import sbt._
import sbtrelease.Utilities._
import sbtrelease._

lazy val publishArtifactsLocal = ReleaseStep(action = runPublishArtifactsLocalAction)

lazy val runPublishArtifactsLocalAction = { st: State =>
  val extracted = st.extract
  val ref = extracted.get(thisProjectRef)
  extracted.runAggregated(publishLocal in Global in ref, st)
}

ReleaseKeys.releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,              // : ReleaseStep
  inquireVersions,                        // : ReleaseStep
  runTest,                                // : ReleaseStep
  setReleaseVersion,                      // : ReleaseStep
  commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
  tagRelease,                             // : ReleaseStep
  publishArtifactsLocal,                  // : ReleaseStep
  setNextVersion,                         // : ReleaseStep
  commitNextVersion,                      // : ReleaseStep
  pushChanges                             // : ReleaseStep
)
