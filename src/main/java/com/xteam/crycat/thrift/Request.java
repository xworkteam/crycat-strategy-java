/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.xteam.crycat.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-04-10")
public class Request implements org.apache.thrift.TBase<Request, Request._Fields>, java.io.Serializable, Cloneable, Comparable<Request> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Request");

  private static final org.apache.thrift.protocol.TField METHOD_FIELD_DESC = new org.apache.thrift.protocol.TField("method", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField PARAMS_FIELD_DESC = new org.apache.thrift.protocol.TField("params", org.apache.thrift.protocol.TType.MAP, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RequestStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RequestTupleSchemeFactory();

  public java.lang.String method; // required
  public java.util.Map<java.lang.String,java.lang.String> params; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    METHOD((short)1, "method"),
    PARAMS((short)2, "params");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // METHOD
          return METHOD;
        case 2: // PARAMS
          return PARAMS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.METHOD, new org.apache.thrift.meta_data.FieldMetaData("method", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARAMS, new org.apache.thrift.meta_data.FieldMetaData("params", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Request.class, metaDataMap);
  }

  public Request() {
  }

  public Request(
    java.lang.String method,
    java.util.Map<java.lang.String,java.lang.String> params)
  {
    this();
    this.method = method;
    this.params = params;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Request(Request other) {
    if (other.isSetMethod()) {
      this.method = other.method;
    }
    if (other.isSetParams()) {
      java.util.Map<java.lang.String,java.lang.String> __this__params = new java.util.HashMap<java.lang.String,java.lang.String>(other.params);
      this.params = __this__params;
    }
  }

  public Request deepCopy() {
    return new Request(this);
  }

  @Override
  public void clear() {
    this.method = null;
    this.params = null;
  }

  public java.lang.String getMethod() {
    return this.method;
  }

  public Request setMethod(java.lang.String method) {
    this.method = method;
    return this;
  }

  public void unsetMethod() {
    this.method = null;
  }

  /** Returns true if field method is set (has been assigned a value) and false otherwise */
  public boolean isSetMethod() {
    return this.method != null;
  }

  public void setMethodIsSet(boolean value) {
    if (!value) {
      this.method = null;
    }
  }

  public int getParamsSize() {
    return (this.params == null) ? 0 : this.params.size();
  }

  public void putToParams(java.lang.String key, java.lang.String val) {
    if (this.params == null) {
      this.params = new java.util.HashMap<java.lang.String,java.lang.String>();
    }
    this.params.put(key, val);
  }

  public java.util.Map<java.lang.String,java.lang.String> getParams() {
    return this.params;
  }

  public Request setParams(java.util.Map<java.lang.String,java.lang.String> params) {
    this.params = params;
    return this;
  }

  public void unsetParams() {
    this.params = null;
  }

  /** Returns true if field params is set (has been assigned a value) and false otherwise */
  public boolean isSetParams() {
    return this.params != null;
  }

  public void setParamsIsSet(boolean value) {
    if (!value) {
      this.params = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case METHOD:
      if (value == null) {
        unsetMethod();
      } else {
        setMethod((java.lang.String)value);
      }
      break;

    case PARAMS:
      if (value == null) {
        unsetParams();
      } else {
        setParams((java.util.Map<java.lang.String,java.lang.String>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case METHOD:
      return getMethod();

    case PARAMS:
      return getParams();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case METHOD:
      return isSetMethod();
    case PARAMS:
      return isSetParams();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Request)
      return this.equals((Request)that);
    return false;
  }

  public boolean equals(Request that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_method = true && this.isSetMethod();
    boolean that_present_method = true && that.isSetMethod();
    if (this_present_method || that_present_method) {
      if (!(this_present_method && that_present_method))
        return false;
      if (!this.method.equals(that.method))
        return false;
    }

    boolean this_present_params = true && this.isSetParams();
    boolean that_present_params = true && that.isSetParams();
    if (this_present_params || that_present_params) {
      if (!(this_present_params && that_present_params))
        return false;
      if (!this.params.equals(that.params))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetMethod()) ? 131071 : 524287);
    if (isSetMethod())
      hashCode = hashCode * 8191 + method.hashCode();

    hashCode = hashCode * 8191 + ((isSetParams()) ? 131071 : 524287);
    if (isSetParams())
      hashCode = hashCode * 8191 + params.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Request other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetMethod()).compareTo(other.isSetMethod());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMethod()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.method, other.method);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetParams()).compareTo(other.isSetParams());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParams()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.params, other.params);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Request(");
    boolean first = true;

    sb.append("method:");
    if (this.method == null) {
      sb.append("null");
    } else {
      sb.append(this.method);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("params:");
    if (this.params == null) {
      sb.append("null");
    } else {
      sb.append(this.params);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (method == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'method' was not present! Struct: " + toString());
    }
    if (params == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'params' was not present! Struct: " + toString());
    }
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RequestStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RequestStandardScheme getScheme() {
      return new RequestStandardScheme();
    }
  }

  private static class RequestStandardScheme extends org.apache.thrift.scheme.StandardScheme<Request> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Request struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // METHOD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.method = iprot.readString();
              struct.setMethodIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PARAMS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map0 = iprot.readMapBegin();
                struct.params = new java.util.HashMap<java.lang.String,java.lang.String>(2*_map0.size);
                java.lang.String _key1;
                java.lang.String _val2;
                for (int _i3 = 0; _i3 < _map0.size; ++_i3)
                {
                  _key1 = iprot.readString();
                  _val2 = iprot.readString();
                  struct.params.put(_key1, _val2);
                }
                iprot.readMapEnd();
              }
              struct.setParamsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Request struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.method != null) {
        oprot.writeFieldBegin(METHOD_FIELD_DESC);
        oprot.writeString(struct.method);
        oprot.writeFieldEnd();
      }
      if (struct.params != null) {
        oprot.writeFieldBegin(PARAMS_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.params.size()));
          for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter4 : struct.params.entrySet())
          {
            oprot.writeString(_iter4.getKey());
            oprot.writeString(_iter4.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RequestTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RequestTupleScheme getScheme() {
      return new RequestTupleScheme();
    }
  }

  private static class RequestTupleScheme extends org.apache.thrift.scheme.TupleScheme<Request> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Request struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeString(struct.method);
      {
        oprot.writeI32(struct.params.size());
        for (java.util.Map.Entry<java.lang.String, java.lang.String> _iter5 : struct.params.entrySet())
        {
          oprot.writeString(_iter5.getKey());
          oprot.writeString(_iter5.getValue());
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Request struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.method = iprot.readString();
      struct.setMethodIsSet(true);
      {
        org.apache.thrift.protocol.TMap _map6 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.params = new java.util.HashMap<java.lang.String,java.lang.String>(2*_map6.size);
        java.lang.String _key7;
        java.lang.String _val8;
        for (int _i9 = 0; _i9 < _map6.size; ++_i9)
        {
          _key7 = iprot.readString();
          _val8 = iprot.readString();
          struct.params.put(_key7, _val8);
        }
      }
      struct.setParamsIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

