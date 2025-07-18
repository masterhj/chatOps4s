package chatops4s.slack.models

import io.circe.{Codec, Decoder, Encoder}
import pureconfig.ConfigReader

case class SlackConfig(
                        botToken: String,
                        signingSecret: String,
                        port: Int = 3000
                      ) derives ConfigReader

case class SlackPostMessageRequest(
                                    channel: String,
                                    text: String,
                                    blocks: Option[List[SlackBlock]] = None,
                                    thread_ts: Option[String] = None
                                  ) derives Codec.AsObject

case class SlackBlock(
                       `type`: String,
                       text: Option[SlackText] = None,
                       elements: Option[List[SlackBlockElement]] = None,
                       accessory: Option[SlackBlockElement] = None
                     ) derives Codec.AsObject

case class SlackText(
                      `type`: String,
                      text: String
                    ) derives Codec.AsObject

case class SlackBlockElement(
                              `type`: String,
                              text: Option[SlackText] = None,
                              action_id: Option[String] = None,
                              value: Option[String] = None,
                              style: Option[String] = None
                            ) derives Codec.AsObject

case class SlackPostMessageResponse(
                                     ok: Boolean,
                                     channel: Option[String] = None,
                                     ts: Option[String] = None,
                                     message: Option[SlackMessage] = None,
                                     error: Option[String] = None
                                   ) derives Codec.AsObject

case class SlackMessage(
                         text: String,
                         user: Option[String] = None,
                         ts: String,
                         thread_ts: Option[String] = None
                       ) derives Codec.AsObject

case class SlackInteractionPayload(
                                    `type`: String,
                                    user: SlackUser,
                                    container: SlackContainer,
                                    trigger_id: String,
                                    team: SlackTeam,
                                    channel: SlackChannel,
                                    message: Option[SlackMessage] = None,
                                    actions: Option[List[SlackAction]] = None,
                                    response_url: Option[String] = None
                                  ) derives Codec.AsObject

case class SlackUser(
                      id: String,
                      name: String
                    ) derives Codec.AsObject

case class SlackTeam(
                      id: String,
                      domain: String
                    ) derives Codec.AsObject

case class SlackChannel(
                         id: String,
                         name: String
                       ) derives Codec.AsObject

case class SlackContainer(
                           `type`: String,
                           message_ts: Option[String] = None
                         ) derives Codec.AsObject

case class SlackAction(
                        action_id: String,
                        block_id: Option[String] = None,
                        text: SlackText,
                        value: Option[String] = None,
                        `type`: String,
                        action_ts: String
                      ) derives Codec.AsObject