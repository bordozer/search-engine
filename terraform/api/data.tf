data "aws_vpc" "vpc" {
    id = var.vpc
}

data "aws_subnet" "subnet1" {
//  id = element(var.subnets, 0).id
  id = "subnet-08d6e761"
//  count = length(var.subnets)
//  id = element(var.subnets, count.index)
  vpc_id = data.aws_vpc.vpc.id
}

data "aws_s3_bucket" "s3_bucket_artifacts" {
  bucket = var.s3_bucket_artifacts
}

data "aws_s3_bucket" "s3_bucket_logs" {
  bucket = var.s3_bucket_logs
}
