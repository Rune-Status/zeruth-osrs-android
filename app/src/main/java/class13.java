import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;
import net.runelite.rs.Reflection;

@ObfuscatedName("n")
public class class13 {
	@ObfuscatedName("fu")
	@ObfuscatedSignature(
		signature = "Lko;"
	)
	@Export("fontPlain12")
	static Font fontPlain12;

	@ObfuscatedName("e")
	@ObfuscatedSignature(
		signature = "(Lkp;IB)V",
		garbageValue = "86"
	)
	@Export("readReflectionCheck")
	public static void readReflectionCheck(Buffer var0, int var1) {
		ReflectionCheck var2 = new ReflectionCheck();
		var2.size = var0.readUnsignedByte();
		var2.id = var0.readInt();
		var2.operations = new int[var2.size];
		var2.creationErrors = new int[var2.size];
		var2.fields = new Field[var2.size];
		var2.intReplaceValues = new int[var2.size];
		var2.methods = new Method[var2.size];
		var2.arguments = new byte[var2.size][][];

		for (int var3 = 0; var3 < var2.size; ++var3) {
			try {
				int var4 = var0.readUnsignedByte();
				String var5;
				String var6;
				int var7;
				if (var4 != 0 && var4 != 1 && var4 != 2) {
					if (var4 == 3 || var4 == 4) {
						var5 = var0.readStringCp1252NullTerminated();
						var6 = var0.readStringCp1252NullTerminated();
						var7 = var0.readUnsignedByte();
						String[] var8 = new String[var7];

						for (int var9 = 0; var9 < var7; ++var9) {
							var8[var9] = var0.readStringCp1252NullTerminated();
						}

						String var26 = var0.readStringCp1252NullTerminated();
						byte[][] var10 = new byte[var7][];
						int var11;
						if (var4 == 3) {
							for (int var12 = 0; var12 < var7; ++var12) {
								var11 = var0.readInt();
								var10[var12] = new byte[var11];
								var0.readBytes(var10[var12], 0, var11);
							}
						}

						var2.operations[var3] = var4;
						Class[] var27 = new Class[var7];

						for (var11 = 0; var11 < var7; ++var11) {
							var27[var11] = class223.loadClassFromDescriptor(var8[var11]);
						}

						Class var13 = class223.loadClassFromDescriptor(var26);
						if (class223.loadClassFromDescriptor(var5).getClassLoader() == null) {
							throw new SecurityException();
						}

						Method[] var14 = class223.loadClassFromDescriptor(var5).getDeclaredMethods();
						Method[] var15 = var14;

						for (int var16 = 0; var16 < var15.length; ++var16) {
							Method var17 = var15[var16];
							if (Reflection.getMethodName(var17).equals(var6)) {
								Class[] var18 = Reflection.getParameterTypes(var17);
								if (var18.length == var27.length) {
									boolean var19 = true;

									for (int var20 = 0; var20 < var27.length; ++var20) {
										if (var18[var20] != var27[var20]) {
											var19 = false;
											break;
										}
									}

									if (var19 && var13 == var17.getReturnType()) {
										var2.methods[var3] = var17;
									}
								}
							}
						}

						var2.arguments[var3] = var10;
					}
				} else {
					var5 = var0.readStringCp1252NullTerminated();
					var6 = var0.readStringCp1252NullTerminated();
					var7 = 0;
					if (var4 == 1) {
						var7 = var0.readInt();
					}

					var2.operations[var3] = var4;
					var2.intReplaceValues[var3] = var7;
					if (class223.loadClassFromDescriptor(var5).getClassLoader() == null) {
						throw new SecurityException();
					}

					var2.fields[var3] = Reflection.findField(class223.loadClassFromDescriptor(var5), var6);
				}
			} catch (ClassNotFoundException var21) {
				var2.creationErrors[var3] = -1;
			} catch (SecurityException var22) {
				var2.creationErrors[var3] = -2;
			} catch (NullPointerException var23) {
				var2.creationErrors[var3] = -3;
			} catch (Exception var24) {
				var2.creationErrors[var3] = -4;
			} catch (Throwable var25) {
				var2.creationErrors[var3] = -5;
			}
		}

		class96.reflectionChecks.addFirst(var2);
	}

	@ObfuscatedName("m")
	@ObfuscatedSignature(
		signature = "(IB)I",
		garbageValue = "0"
	)
	@Export("Messages_getNextChatID")
	static int Messages_getNextChatID(int var0) {
		Message var1 = (Message)Messages.Messages_hashTable.get((long)var0);
		if (var1 == null) {
			return -1;
		} else {
			return var1.previousDual == Messages.Messages_queue.sentinel ? -1 : ((Message)var1.previousDual).count;
		}
	}

	@ObfuscatedName("fi")
	@ObfuscatedSignature(
		signature = "(II)V",
		garbageValue = "859526666"
	)
	@Export("forceDisconnect")
	static final void forceDisconnect(int var0) {
		class30.logOut();
		switch(var0) {
		case 1:
			WorldMapCacheName.method634();
			break;
		case 2:
			NPC.method2079();
		}

	}
}