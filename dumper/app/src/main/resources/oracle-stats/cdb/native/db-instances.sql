-- Copyright 2022-2025 Google LLC
-- Copyright 2013-2021 CompilerWorks
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
SELECT
  A.inst_id "InstId",
  A.instance_number "InstanceNumber",
  A.instance_name "InstanceName",
  A.host_name "HostName",
  A.version "Version",
  A.version_legacy "VersionLegacy",
  A.version_full "VersionFull",
  A.startup_time "StartupTime",
  A.status "Status",
  A.parallel "Parallel",
  A.thread# "ThreadNumber",
  A.archiver "Archiver",
  A.log_switch_wait "LogSwitchWait",
  A.logins "Logins",
  A.shutdown_pending "ShutdownPending",
  A.database_status "DatabaseStatus",
  A.instance_role "InstanceRole",
  A.active_state "ActiveState",
  A.blocked "Blocked",
  A.con_id "ConId",
  A.instance_mode "InstanceMode",
  A.edition "Edition",
  A.family "Family",
  A.database_type "DatabaseType"
FROM gv$instance A
