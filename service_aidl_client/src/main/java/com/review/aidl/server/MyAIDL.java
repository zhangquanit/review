/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.review.aidl.server;
//寮曞叆

public interface MyAIDL extends android.os.IInterface {
    /**
     * Local-side IPC implementation stub class.
     */
    public static abstract class Stub extends android.os.Binder implements MyAIDL {
        private static final String DESCRIPTOR = "com.review.aidl.server.MyAIDL";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.review.aidl.server.MyAIDL interface,
         * generating a proxy if needed.
         */
        public static MyAIDL asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof MyAIDL))) {
                return ((MyAIDL) iin);
            }
            return new Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_getInfor: {
                    data.enforceInterface(descriptor);
                    String _arg0;
                    _arg0 = data.readString();
                    String _result = this.getInfor(_arg0);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                }
                case TRANSACTION_greet: {
                    data.enforceInterface(descriptor);
                    com.review.aidl.server.bean.Person _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = com.review.aidl.server.bean.Person.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    String _result = this.greet(_arg0);
                    reply.writeNoException();
                    reply.writeString(_result);
                    return true;
                }
                case TRANSACTION_getPerson: {
                    data.enforceInterface(descriptor);
                    java.util.List<com.review.aidl.server.bean.Person> _result = this.getPerson();
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements MyAIDL {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public String getInfor(String s) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(s);
                    mRemote.transact(Stub.TRANSACTION_getInfor, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public String greet(com.review.aidl.server.bean.Person person) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((person != null)) {
                        _data.writeInt(1);
                        person.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_greet, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public java.util.List<com.review.aidl.server.bean.Person> getPerson() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.util.List<com.review.aidl.server.bean.Person> _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getPerson, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.createTypedArrayList(com.review.aidl.server.bean.Person.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        static final int TRANSACTION_getInfor = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_greet = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
        static final int TRANSACTION_getPerson = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    }

    public String getInfor(String s) throws android.os.RemoteException;

    public String greet(com.review.aidl.server.bean.Person person) throws android.os.RemoteException;

    public java.util.List<com.review.aidl.server.bean.Person> getPerson() throws android.os.RemoteException;
}
