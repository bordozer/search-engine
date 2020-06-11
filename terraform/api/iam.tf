resource "aws_iam_role" "service_role" {
  name = "tf-${var.service_instance_name}-role"

  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "codebuild.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
EOF
}

resource "aws_iam_role_policy" "vpc_policy" {
  role = aws_iam_role.service_role.name
  name = "tf-${var.service_instance_name}-vpc-policy"

  policy = <<POLICY
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "ec2:CreateNetworkInterface",
                "ec2:DescribeDhcpOptions",
                "ec2:DescribeNetworkInterfaces",
                "ec2:DeleteNetworkInterface",
                "ec2:DescribeSubnets",
                "ec2:DescribeSecurityGroups",
                "ec2:DescribeVpcs"
            ],
            "Resource": "*"
        },
        {
            "Effect": "Allow",
            "Action": [
                "ec2:CreateNetworkInterfacePermission"
            ],
            "Resource": "arn:aws:ec2:eu-west-3:899415655760:network-interface/*",
            "Condition": {
                "StringEquals": {
                    "ec2:Subnet": [
                        "${data.aws_subnet.subnet1.arn}"
                    ],
                    "ec2:AuthorizedService": "codebuild.amazonaws.com"
                }
            }
        }
    ]
}
POLICY
}

resource "aws_iam_role_policy" "base_policy" {
  role = aws_iam_role.service_role.name
  name = "tf-${var.service_instance_name}-log-policy"

  policy = <<POLICY
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Resource": [
                "arn:aws:logs:eu-west-3:899415655760:log-group:/aws/codebuild/search-engine-build",
                "arn:aws:logs:eu-west-3:899415655760:log-group:/aws/codebuild/search-engine-build:*"
            ],
            "Action": [
                "logs:CreateLogGroup",
                "logs:CreateLogStream",
                "logs:PutLogEvents"
            ]
        },
        {
            "Effect": "Allow",
            "Resource": [
                "arn:aws:s3:::codepipeline-eu-west-3-*"
            ],
            "Action": [
                "s3:PutObject",
                "s3:GetObject",
                "s3:GetObjectVersion",
                "s3:GetBucketAcl",
                "s3:GetBucketLocation"
            ]
        },
        {
            "Effect": "Allow",
            "Resource": [
                "${data.aws_s3_bucket.s3_bucket_artifacts.arn}",
                "${data.aws_s3_bucket.s3_bucket_artifacts.arn}/*",
                "${data.aws_s3_bucket.s3_bucket_logs.arn}",
                "${data.aws_s3_bucket.s3_bucket_logs.arn}/*"
            ],
            "Action": [
                "s3:PutObject",
                "s3:GetBucketAcl",
                "s3:GetBucketLocation"
            ]
        },
        {
            "Effect": "Allow",
            "Action": [
                "codebuild:CreateReportGroup",
                "codebuild:CreateReport",
                "codebuild:UpdateReport",
                "codebuild:BatchPutTestCases"
            ],
            "Resource": [
                "arn:aws:codebuild:eu-west-3:899415655760:report-group/search-engine-build-*"
            ]
        }
    ]
}
POLICY
}
