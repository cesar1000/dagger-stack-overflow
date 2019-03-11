package com.twitter.example;

import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import javax.inject.Inject;

class Example {
  static class ObjectA {
    @Inject
    ObjectA() {
    }
  }

  @Subcomponent
  interface SubcomponentA {
    ObjectA getObjectA();

    @Subcomponent.Builder
    interface Builder {
      SubcomponentA build();
    }
  }

  @Module(subcomponents = SubcomponentA.class)
  static class ModuleA {
    @Provides
    SubcomponentA getSubcomponentA(SubcomponentA.Builder builder) {
      return builder.build();
    }

    @Provides
    ObjectA getObjectA(SubcomponentA subcomponent) {
      return subcomponent.getObjectA();
    }
  }

  @Component(modules = ModuleA.class)
  interface ComponentA {
    ObjectA getObjectA();
  }

  public static void main(String[] args) {
    DaggerExample_ComponentA.builder().build().getObjectA();
  }
}
