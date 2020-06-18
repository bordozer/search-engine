variable "service_name" { default = "search-engine-api" }
variable "service_instance_name" {}
variable "environment_name" {}

/* Amazon account network parameters */
variable "vpc" { default = "vpc-74c2c81d" }
variable "aws_region" { default = "eu-west-3" }
variable "availability_zones" {
        default = [
          "eu-west-3a",
          "eu-west-3b",
          "eu-west-3c"
        ]
}
variable "subnets" {
  default = [
    "subnet-08d6e761",
    "subnet-f2d79f89",
    "subnet-096bf644"
  ]
}

/* S3 buckets */
variable "s3_bucket_artifacts" { default = "bordozer-artifacts" }
variable "s3_bucket_logs" { default = "bordozer-logs" }

/* GitHub */
variable "project_source" { default = "https://github.com/bordozer/search-engine.git" }
variable "branch" { default = "master" }
variable "access_token_ssm_parameter_name" { default = "github-access-token" }

variable "sns_topic_name" { default = "search-engine-notification" }

/* common vars */
locals {
  common_tags = {
    Name = var.service_instance_name
    ServiceName = var.service_name
    Environment = var.environment_name
  }
}
