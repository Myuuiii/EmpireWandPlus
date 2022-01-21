package moe.myuuiii.empirewandplus.handlers;

import java.util.UUID;

import moe.myuuiii.empirewandplus.Data;

public class CloudHandler {
	public static void DisableCloud(UUID uniqueId) {

		if (Data.bloodCloudUsers.contains(uniqueId))
			Data.bloodCloudUsers.remove(uniqueId);

		if (Data.celestialCloudUsers.contains(uniqueId))
			Data.celestialCloudUsers.remove(uniqueId);

		if (Data.empireCloudUsers.contains(uniqueId))
			Data.empireCloudUsers.remove(uniqueId);

		if (Data.poisonCloudUsers.contains(uniqueId))
			Data.poisonCloudUsers.remove(uniqueId);
	}
}
