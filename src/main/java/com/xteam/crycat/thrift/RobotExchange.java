/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.xteam.crycat.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-03-22")
public class RobotExchange implements org.apache.thrift.TBase<RobotExchange, RobotExchange._Fields>, java.io.Serializable, Cloneable, Comparable<RobotExchange> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RobotExchange");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CLAZZ_FIELD_DESC = new org.apache.thrift.protocol.TField("clazz", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField SYMBOLS_FIELD_DESC = new org.apache.thrift.protocol.TField("symbols", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField PARAMS_FIELD_DESC = new org.apache.thrift.protocol.TField("params", org.apache.thrift.protocol.TType.MAP, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RobotExchangeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RobotExchangeTupleSchemeFactory();

  public String id; // optional
  public String clazz; // optional
  public java.util.List<String> symbols; // optional
  public java.util.Map<String,String> params; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    CLAZZ((short)2, "clazz"),
    SYMBOLS((short)3, "symbols"),
    PARAMS((short)4, "params");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

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
        case 1: // ID
          return ID;
        case 2: // CLAZZ
          return CLAZZ;
        case 3: // SYMBOLS
          return SYMBOLS;
        case 4: // PARAMS
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
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.ID,_Fields.CLAZZ,_Fields.SYMBOLS,_Fields.PARAMS};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CLAZZ, new org.apache.thrift.meta_data.FieldMetaData("clazz", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SYMBOLS, new org.apache.thrift.meta_data.FieldMetaData("symbols", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.PARAMS, new org.apache.thrift.meta_data.FieldMetaData("params", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RobotExchange.class, metaDataMap);
  }

  public RobotExchange() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RobotExchange(RobotExchange other) {
    if (other.isSetId()) {
      this.id = other.id;
    }
    if (other.isSetClazz()) {
      this.clazz = other.clazz;
    }
    if (other.isSetSymbols()) {
      java.util.List<String> __this__symbols = new java.util.ArrayList<String>(other.symbols);
      this.symbols = __this__symbols;
    }
    if (other.isSetParams()) {
      java.util.Map<String,String> __this__params = new java.util.HashMap<String,String>(other.params);
      this.params = __this__params;
    }
  }

  public RobotExchange deepCopy() {
    return new RobotExchange(this);
  }

  @Override
  public void clear() {
    this.id = null;
    this.clazz = null;
    this.symbols = null;
    this.params = null;
  }

  public String getId() {
    return this.id;
  }

  public RobotExchange setId(String id) {
    this.id = id;
    return this;
  }

  public void unsetId() {
    this.id = null;
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return this.id != null;
  }

  public void setIdIsSet(boolean value) {
    if (!value) {
      this.id = null;
    }
  }

  public String getClazz() {
    return this.clazz;
  }

  public RobotExchange setClazz(String clazz) {
    this.clazz = clazz;
    return this;
  }

  public void unsetClazz() {
    this.clazz = null;
  }

  /** Returns true if field clazz is set (has been assigned a value) and false otherwise */
  public boolean isSetClazz() {
    return this.clazz != null;
  }

  public void setClazzIsSet(boolean value) {
    if (!value) {
      this.clazz = null;
    }
  }

  public int getSymbolsSize() {
    return (this.symbols == null) ? 0 : this.symbols.size();
  }

  public java.util.Iterator<String> getSymbolsIterator() {
    return (this.symbols == null) ? null : this.symbols.iterator();
  }

  public void addToSymbols(String elem) {
    if (this.symbols == null) {
      this.symbols = new java.util.ArrayList<String>();
    }
    this.symbols.add(elem);
  }

  public java.util.List<String> getSymbols() {
    return this.symbols;
  }

  public RobotExchange setSymbols(java.util.List<String> symbols) {
    this.symbols = symbols;
    return this;
  }

  public void unsetSymbols() {
    this.symbols = null;
  }

  /** Returns true if field symbols is set (has been assigned a value) and false otherwise */
  public boolean isSetSymbols() {
    return this.symbols != null;
  }

  public void setSymbolsIsSet(boolean value) {
    if (!value) {
      this.symbols = null;
    }
  }

  public int getParamsSize() {
    return (this.params == null) ? 0 : this.params.size();
  }

  public void putToParams(String key, String val) {
    if (this.params == null) {
      this.params = new java.util.HashMap<String,String>();
    }
    this.params.put(key, val);
  }

  public java.util.Map<String,String> getParams() {
    return this.params;
  }

  public RobotExchange setParams(java.util.Map<String,String> params) {
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

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((String)value);
      }
      break;

    case CLAZZ:
      if (value == null) {
        unsetClazz();
      } else {
        setClazz((String)value);
      }
      break;

    case SYMBOLS:
      if (value == null) {
        unsetSymbols();
      } else {
        setSymbols((java.util.List<String>)value);
      }
      break;

    case PARAMS:
      if (value == null) {
        unsetParams();
      } else {
        setParams((java.util.Map<String,String>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case CLAZZ:
      return getClazz();

    case SYMBOLS:
      return getSymbols();

    case PARAMS:
      return getParams();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case CLAZZ:
      return isSetClazz();
    case SYMBOLS:
      return isSetSymbols();
    case PARAMS:
      return isSetParams();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof RobotExchange)
      return this.equals((RobotExchange)that);
    return false;
  }

  public boolean equals(RobotExchange that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_id = true && this.isSetId();
    boolean that_present_id = true && that.isSetId();
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (!this.id.equals(that.id))
        return false;
    }

    boolean this_present_clazz = true && this.isSetClazz();
    boolean that_present_clazz = true && that.isSetClazz();
    if (this_present_clazz || that_present_clazz) {
      if (!(this_present_clazz && that_present_clazz))
        return false;
      if (!this.clazz.equals(that.clazz))
        return false;
    }

    boolean this_present_symbols = true && this.isSetSymbols();
    boolean that_present_symbols = true && that.isSetSymbols();
    if (this_present_symbols || that_present_symbols) {
      if (!(this_present_symbols && that_present_symbols))
        return false;
      if (!this.symbols.equals(that.symbols))
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

    hashCode = hashCode * 8191 + ((isSetId()) ? 131071 : 524287);
    if (isSetId())
      hashCode = hashCode * 8191 + id.hashCode();

    hashCode = hashCode * 8191 + ((isSetClazz()) ? 131071 : 524287);
    if (isSetClazz())
      hashCode = hashCode * 8191 + clazz.hashCode();

    hashCode = hashCode * 8191 + ((isSetSymbols()) ? 131071 : 524287);
    if (isSetSymbols())
      hashCode = hashCode * 8191 + symbols.hashCode();

    hashCode = hashCode * 8191 + ((isSetParams()) ? 131071 : 524287);
    if (isSetParams())
      hashCode = hashCode * 8191 + params.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RobotExchange other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetClazz()).compareTo(other.isSetClazz());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClazz()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.clazz, other.clazz);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetSymbols()).compareTo(other.isSetSymbols());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSymbols()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.symbols, other.symbols);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetParams()).compareTo(other.isSetParams());
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
  public String toString() {
    StringBuilder sb = new StringBuilder("RobotExchange(");
    boolean first = true;

    if (isSetId()) {
      sb.append("id:");
      if (this.id == null) {
        sb.append("null");
      } else {
        sb.append(this.id);
      }
      first = false;
    }
    if (isSetClazz()) {
      if (!first) sb.append(", ");
      sb.append("clazz:");
      if (this.clazz == null) {
        sb.append("null");
      } else {
        sb.append(this.clazz);
      }
      first = false;
    }
    if (isSetSymbols()) {
      if (!first) sb.append(", ");
      sb.append("symbols:");
      if (this.symbols == null) {
        sb.append("null");
      } else {
        sb.append(this.symbols);
      }
      first = false;
    }
    if (isSetParams()) {
      if (!first) sb.append(", ");
      sb.append("params:");
      if (this.params == null) {
        sb.append("null");
      } else {
        sb.append(this.params);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RobotExchangeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RobotExchangeStandardScheme getScheme() {
      return new RobotExchangeStandardScheme();
    }
  }

  private static class RobotExchangeStandardScheme extends org.apache.thrift.scheme.StandardScheme<RobotExchange> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RobotExchange struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.id = iprot.readString();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CLAZZ
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.clazz = iprot.readString();
              struct.setClazzIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // SYMBOLS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.symbols = new java.util.ArrayList<String>(_list0.size);
                String _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = iprot.readString();
                  struct.symbols.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setSymbolsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PARAMS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map3 = iprot.readMapBegin();
                struct.params = new java.util.HashMap<String,String>(2*_map3.size);
                String _key4;
                String _val5;
                for (int _i6 = 0; _i6 < _map3.size; ++_i6)
                {
                  _key4 = iprot.readString();
                  _val5 = iprot.readString();
                  struct.params.put(_key4, _val5);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RobotExchange struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.id != null) {
        if (struct.isSetId()) {
          oprot.writeFieldBegin(ID_FIELD_DESC);
          oprot.writeString(struct.id);
          oprot.writeFieldEnd();
        }
      }
      if (struct.clazz != null) {
        if (struct.isSetClazz()) {
          oprot.writeFieldBegin(CLAZZ_FIELD_DESC);
          oprot.writeString(struct.clazz);
          oprot.writeFieldEnd();
        }
      }
      if (struct.symbols != null) {
        if (struct.isSetSymbols()) {
          oprot.writeFieldBegin(SYMBOLS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.symbols.size()));
            for (String _iter7 : struct.symbols)
            {
              oprot.writeString(_iter7);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.params != null) {
        if (struct.isSetParams()) {
          oprot.writeFieldBegin(PARAMS_FIELD_DESC);
          {
            oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.params.size()));
            for (java.util.Map.Entry<String, String> _iter8 : struct.params.entrySet())
            {
              oprot.writeString(_iter8.getKey());
              oprot.writeString(_iter8.getValue());
            }
            oprot.writeMapEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RobotExchangeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RobotExchangeTupleScheme getScheme() {
      return new RobotExchangeTupleScheme();
    }
  }

  private static class RobotExchangeTupleScheme extends org.apache.thrift.scheme.TupleScheme<RobotExchange> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RobotExchange struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetClazz()) {
        optionals.set(1);
      }
      if (struct.isSetSymbols()) {
        optionals.set(2);
      }
      if (struct.isSetParams()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetId()) {
        oprot.writeString(struct.id);
      }
      if (struct.isSetClazz()) {
        oprot.writeString(struct.clazz);
      }
      if (struct.isSetSymbols()) {
        {
          oprot.writeI32(struct.symbols.size());
          for (String _iter9 : struct.symbols)
          {
            oprot.writeString(_iter9);
          }
        }
      }
      if (struct.isSetParams()) {
        {
          oprot.writeI32(struct.params.size());
          for (java.util.Map.Entry<String, String> _iter10 : struct.params.entrySet())
          {
            oprot.writeString(_iter10.getKey());
            oprot.writeString(_iter10.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RobotExchange struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.id = iprot.readString();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.clazz = iprot.readString();
        struct.setClazzIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list11 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.symbols = new java.util.ArrayList<String>(_list11.size);
          String _elem12;
          for (int _i13 = 0; _i13 < _list11.size; ++_i13)
          {
            _elem12 = iprot.readString();
            struct.symbols.add(_elem12);
          }
        }
        struct.setSymbolsIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TMap _map14 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.params = new java.util.HashMap<String,String>(2*_map14.size);
          String _key15;
          String _val16;
          for (int _i17 = 0; _i17 < _map14.size; ++_i17)
          {
            _key15 = iprot.readString();
            _val16 = iprot.readString();
            struct.params.put(_key15, _val16);
          }
        }
        struct.setParamsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
