package io.invertase.firebase.app;

/*
 * Copyright (c) 2016-present Invertase Limited & Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this library except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

import androidx.annotation.Nullable;

import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;

import java.util.HashMap;
import java.util.Map;

import io.invertase.firebase.BuildConfig;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.invertase.firebase.utils.ReactNativeFirebaseUtilsModule;
import io.invertase.firebase.utils.ReactNativeFirebaseUtilsModuleImpl;

@SuppressWarnings("unused")
public class ReactNativeFirebaseAppPackage extends TurboReactPackage {
  @Nullable
  @Override
  public NativeModule getModule(String name, ReactApplicationContext reactContext) {
    if (name.equals(ReactNativeFirebaseModule.getModuleName(ReactNativeFirebaseUtilsModuleImpl.TAG))) {
      return new ReactNativeFirebaseUtilsModule(reactContext);
    } else if (name.equals(ReactNativeFirebaseModule.getModuleName(ReactNativeFirebaseAppModuleImpl.TAG))) {
      return new ReactNativeFirebaseAppModule(reactContext);
    } {
      return null;
    }
  }

  @Override
  public ReactModuleInfoProvider getReactModuleInfoProvider() {
    String utilsModuleName = ReactNativeFirebaseModule.getModuleName(ReactNativeFirebaseUtilsModuleImpl.TAG);
    String appModuleName = ReactNativeFirebaseModule.getModuleName(ReactNativeFirebaseAppModuleImpl.TAG);

    return () -> {
      boolean isTurboModule = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
      final Map<String, ReactModuleInfo> moduleInfos = new HashMap<>();
      moduleInfos.put(
        utilsModuleName,
        new ReactModuleInfo(
          utilsModuleName,
          utilsModuleName,
          false, // canOverrideExistingModule
          false, // needsEagerInit
          true, // hasConstants
          false, // isCxxModule
          isTurboModule // isTurboModule
        ));

      moduleInfos.put(
        appModuleName,
        new ReactModuleInfo(
          appModuleName,
          appModuleName,
          false, // canOverrideExistingModule
          false, // needsEagerInit
          true, // hasConstants
          false, // isCxxModule
          isTurboModule // isTurboModule
        ));
      return moduleInfos;
    };
  }
//  @Nonnull
//  @Override
//  public List<NativeModule> createNativeModules(@Nonnull ReactApplicationContext reactContext) {
//    if (ReactNativeFirebaseApp.getApplicationContext() == null) {
//      ReactNativeFirebaseApp.setApplicationContext(reactContext.getApplicationContext());
//    }
//    List<NativeModule> modules = new ArrayList<>();
//    modules.add(new ReactNativeFirebaseAppModule(reactContext));
//    modules.add(new ReactNativeFirebaseUtilsModule(reactContext));
//    return modules;
//  }
//
//  @Nonnull
//  @Override
//  public List<ViewManager> createViewManagers(@Nonnull ReactApplicationContext reactContext) {
//    return Collections.emptyList();
//  }
}
